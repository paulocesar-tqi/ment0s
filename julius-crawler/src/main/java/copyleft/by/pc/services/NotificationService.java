package copyleft.by.pc.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import com.google.android.gcm.server.Sender;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.common.entities.NotificationPlan;
import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.common.entities.Word;
import copyleft.by.pc.pojos.Notification;

public class NotificationService {

	private static final Log log = LogFactory.getLog(NotificationService.class);

	@Autowired
	private GenericDao dao;

	@Autowired
	private GCMNotificationSender sender;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Value("${notification.enabled}") 
	private Boolean isNotificationsEnabled;
	
	private NotificationPlan notificationPlan = new NotificationPlan();
	private boolean waitForNotificate = true;
	
	@Async
	public void addPostsToNotificationMountQueue(final ArrayList<Post> items) {
		if(isNotificationsEnabled) {
			jmsTemplate.send("notification-process-queue", new MessageCreator() {
				@Override
	            public Message createMessage(Session session) throws JMSException {
	                return session.createObjectMessage(items); 
	            }
	        });
		}
	}

	@Async
	public void addNotificationToSendQueue(final Notification notification) {
		if(isNotificationsEnabled) {
			jmsTemplate.send("notification-send-queue", new MessageCreator() {
				@Override
	            public Message createMessage(Session session) throws JMSException {
	                return session.createObjectMessage(notification); 
	            }
	        });
		}
	}

	
	@Scheduled(fixedDelayString="${words.runevery}",initialDelay=10000)
	public void getNotificationPlan() {
		waitForNotificate = true;
		notificationPlan = dao.getWordsAndIds();
		waitForNotificate = false;
	}
	
	@JmsListener(destination = "notification-process-queue", containerFactory = "myJmsContainerFactory")
	public void checkPostsToNotify(final ArrayList<Post> items) throws InterruptedException {
		while(waitForNotificate){
    		Thread.sleep(1000);
    	}
		for(Post post : items) {
			Notification notification = new Notification("Promobugs", removeTags(post.getHtml()), notificationPlan.getIdsAndroidNoFilter(), notificationPlan.getIdsIosNoFilter(), notificationPlan.getIdsWpNoFilter());
			String postText = normalize(post.getHtml());
			for(Word word : notificationPlan.getWords()) {
				if(postText.indexOf(word.getWord()) != -1) {
					notification.getAndroidIdsToSend().addAll(word.getIdsAndroid());
					notification.getIosIdsToSend().addAll(word.getIdsIos());
					notification.getWpIdsToSend().addAll(word.getIdsWp());
				}
			}
			addNotificationToSendQueue(notification);
		}
	}
	
	@JmsListener(destination = "notification-send-queue", containerFactory = "myJmsContainerFactory")
	public void sendNotifications(final Notification notification) throws InterruptedException {
		
		if(!CollectionUtils.isEmpty(notification.getAndroidIdsToSend())) {
			log.info("Sending notification:\n" + notification);
			sender.sendNotification(notification);
		}		
		//TODO: Send notification for IOS and WP
	}
	
	private String normalize(String text) {
		text = text.replaceAll("\\<.*?\\>", "");
		text = Normalizer.normalize(text.toLowerCase(), Normalizer.Form.NFD);
		text = text.replaceAll("[^\\p{ASCII}]", "");
		text = text.replaceAll("[^a-z0-9]", "");
		log.info("post nomalized: " + text);
		
		return text;
	}
	
	private String removeTags(String text) {
		text = text.replaceAll("\\<.*?\\>", "");
	  	text = text.replaceAll("(http+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", "");
	  	return text;
	}

	
	public static void main(String[] args) throws IOException {
		Notification notification = new Notification("Promobugs", 
													//"A coordenadora do Programa de Pós-graduação em Ciência da Computação (PPGCO) da Faculdade de Computação (FACOM) da Universidade Federal de Uberlândia (UFU) faz saber a todos quantos virem o presente edital",
				"paulo teste 2 paulo teste 2 paulo teste 2 paulo teste 2 paulo teste 2 ",
													null, null, null);

		Sender gcmSender = new Sender("AIzaSyDdQORbPTMuO9ZPIM4E5VzqXwW-MNX--qM");
		gcmSender.sendNoRetry(createMessage(notification), "APA91bFpik20ovAf-iO2GqV456T-YvS5p7WsZHhZpvMqCGWgzikQdRX7J2Vbophunifi4S8kc73osydfoEQ1TT9_La3_RButKWemplfyLKcHWxwEQi-goLpxgxX6YU3a-9hC1FtBA6hV");
		//System.out.println(String.valueOf(System.currentTimeMillis()));
		
	}
	
	private static com.google.android.gcm.server.Message createMessage(Notification notification) {
		final com.google.android.gcm.server.Message.Builder messageBuilder = new com.google.android.gcm.server.Message.Builder();
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
		
//	public static void main(String[] args) {
//		  	String text = "Tábua de Corte Hauskraft http://www.americanas.com.br/produto Vermelha 25cm por R$ 10,95<br/> <a href=\"javascript:window.open('http://www.americanas.com.br/produto/122376946/tabua-de-corte-hauskraft-vermelha-25cm?franq=AFL-03-77214','_system', 'location=yes')\">http://www.americanas.com.br/produto/122376946/tabua-de-corte-hauskraft-vermelha-25cm?franq=AFL-03-77214</a>";
//		  	text = text.replaceAll("\\<.*?\\>", "");
//		  	text = text.replaceAll("(http+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", "");
//			//text = Normalizer.normalize(text.toLowerCase(), Normalizer.Form.NFD);
//			//text = text.replaceAll("[^\\p{ASCII}]", "");
//			//text = text.replaceAll("[^a-z0-9]", "");
//			//log.info("post nomalized: " + text);
//			System.out.println(text);
//	}

}
