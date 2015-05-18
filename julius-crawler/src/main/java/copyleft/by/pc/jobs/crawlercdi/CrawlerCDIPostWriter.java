package copyleft.by.pc.jobs.crawlercdi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.listeners.NotificationService;

public class CrawlerCDIPostWriter implements ItemWriter<Post> {
	private static final Log log = LogFactory.getLog(CrawlerCDIPostWriter.class);
	
	@PersistenceContext
	@Autowired
	private EntityManager em;
	
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public void write(List<? extends Post> posts) throws Exception {
		
		Calendar last3Days = Calendar.getInstance();
		last3Days.add(Calendar.DATE, -3);
		
		int insertedCount = 0;
		List<Post> newPosts = new ArrayList<Post>(); 
		for(Post post : posts) {
			if(post != null && last3Days.getTime().before(post.getPublicationDate())) {
				log.info("Insertion post: " + post.getTitle());
				em.persist(post);
				++insertedCount;
				newPosts.add(post);
			}
		}
		
		if(insertedCount > 0) {
			em.flush();
			notificateAndroidUsers(newPosts);
		}

		log.info("CdiWriter: " + insertedCount + " novos posts inseridos.");
	}
	
	@Async
	private void notificateAndroidUsers(List<Post> newPosts) {
//		List<Post> posts = new ArrayList<Post>();
//		posts.add(newPosts.get(0));
//		notificationService.notificateAndroidUsers(posts);
		notificationService.notificateAndroidUsers(newPosts);
	}

}
