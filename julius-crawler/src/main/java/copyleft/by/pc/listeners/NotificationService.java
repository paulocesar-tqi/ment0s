package copyleft.by.pc.listeners;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.android.gcm.server.Sender;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.pojos.Notification;
import copyleft.by.pc.services.GCMNotificationSender;

public class NotificationService {

	private static final Log log = LogFactory.getLog(NotificationService.class);

	@Autowired
	private GenericDao dao;

	@Autowired
	private Sender gcmSender;

	@Autowired
	private GCMNotificationSender sender;
	
	@Autowired
	@Value("${notification.enabled}") 
	private Boolean isNotificationsEnabled;

	public void notificateAndroidUsers(List<Post> items) {
	
		if(isNotificationsEnabled) {
			//Retrieve ids
			List<String> ids = dao.getRegistrationIdsByPlatform("android");

			if (ids != null && ids.size() > 0) {
				for(Post post : items) {
					Notification notification = new Notification();
					notification.setBadge(1);
					notification.setRegistrationIdsToSend(ids);
					notification.setTitle("Teste mensagem!");
					notification.setMessage(post.getTitle());
					
					sender.sendNotification(notification);
				}
			}
		}
	}

}
