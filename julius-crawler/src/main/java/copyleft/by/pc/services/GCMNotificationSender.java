package copyleft.by.pc.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.pojos.Notification;

@Service
public class GCMNotificationSender {

	private static final Log log = LogFactory.getLog(GCMNotificationSender.class);

	private final int MAX_RETRIES;

	private final int MAX_MULTICAST_SIZE;
	
	private final Executor THREAD_POOL;

	private final GenericDao dao;

	private final Sender gcmSender;

	@Autowired
	public GCMNotificationSender(GenericDao dao, Sender gcmSender, @Value("${notification.android.max.retries}")  int maxRetries, @Value("${notification.android.max.multicast.size}") int maxMulticastRetries, @Value("${notification.android.thread.pool.size}")  int threadPoolSize) {
		this.dao = dao;
		this.gcmSender = gcmSender;
		this.MAX_RETRIES = maxRetries;
		this.MAX_MULTICAST_SIZE = maxMulticastRetries;
		this.THREAD_POOL = Executors.newFixedThreadPool(threadPoolSize);
	}

	public void sendNotification(Notification notification) {
		sendAsyncNotification(notification, notification.getAndroidIdsToSend());
	}

	private void sendAsyncNotification(Notification notification, List<String> validDeviceIds) {
		final Message message = createMessage(notification);
		if (validDeviceIds.size() > MAX_MULTICAST_SIZE) {
			final List<String> deviceIdsToSend = validDeviceIds.subList(0, MAX_MULTICAST_SIZE);
			THREAD_POOL.execute(new NotificationSenderThread(message, deviceIdsToSend));
			sendAsyncNotification(notification, validDeviceIds.subList(MAX_MULTICAST_SIZE, validDeviceIds.size()));
		} else {
			THREAD_POOL.execute(new NotificationSenderThread(message, validDeviceIds));
		}
	}

	private void analyzeResult(Result result, String deviceId) {
		final String messageId = result.getMessageId();
		if (messageId != null) {
			log.info("Succesfully sent message to device: " + deviceId + "; messageId = " + messageId);
			evalIfSameDeviceHasMoreThanOneRegistrationId(result, deviceId);
		} else {
			evalErrorCode(result, deviceId);
		}
	}

	private void evalIfSameDeviceHasMoreThanOneRegistrationId(Result result, String deviceId) {
		String canonicalRegId = result.getCanonicalRegistrationId();
		if (canonicalRegId != null) {
			log.info("Device with id {" + deviceId + "} has more than one registration id. Updating data.");
			dao.removeUser(deviceId);
			dao.createOrUpdateUser(canonicalRegId, "android");
		}
	}

	private void evalErrorCode(Result result, String deviceId) {
		final String error = result.getErrorCodeName();
		if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
			log.info("Unkown device id {" + deviceId + "}");
			dao.removeUser(deviceId);
		} else {
			log.error("Error sending message to {"+deviceId+"} " + error);
		}
	}

	private Message createMessage(Notification notification) {
		final Message.Builder messageBuilder = new Message.Builder();
		messageBuilder.addData("title", notification.getTitle());
		
		String message = "";
		try {
			message = URLEncoder.encode(notification.getMessage(), "UTF-8");
		} catch (UnsupportedEncodingException e) {}
		
		messageBuilder.addData("message", message);
		
		messageBuilder.addData("notId", String.valueOf(System.currentTimeMillis()));
		
		//messageBuilder.addData("msgcnt", notification.getBadge().toString());
		return messageBuilder.build();
	}

	private class NotificationSenderThread implements Runnable {

		private final Message message;

		private final List<String> deviceIdsToSend;

		NotificationSenderThread(Message message, List<String> deviceIdsToSend) {
			this.message = message;
			this.deviceIdsToSend = deviceIdsToSend;
		}

		public void run() {
			MulticastResult multicastResult;
			try {
				multicastResult = gcmSender.send(message, deviceIdsToSend, MAX_RETRIES);
			} catch (IOException e) {
				log.error("Error sending messages to GCM", e);
				return;
			}
			List<Result> results = multicastResult.getResults();
			for (int i = 0; i < deviceIdsToSend.size(); i++) {
				Result result = results.get(i);
				String deviceId = deviceIdsToSend.get(i);
				analyzeResult(result, deviceId);
			}
		}

	}
}
