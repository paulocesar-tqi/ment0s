package copyleft.by.pc.jobs.crawlergatry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.services.NotificationService;

public class CrawlerGatryPostWriter implements ItemWriter<Post> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostWriter.class);
	
	@PersistenceContext
	@Autowired
	private EntityManager em;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private MemcachedClient cache;

	@Override
	public void write(List<? extends Post> posts) throws Exception {
		
		Calendar last2Days = Calendar.getInstance();
		last2Days.add(Calendar.DATE, -2);
		
		int insertedCount = 0;
		ArrayList<Post> newPosts = new ArrayList<Post>(); 
		for(Post post : posts) {
			if(post != null && last2Days.getTime().before(post.getPublicationDate())) {
				em.persist(post);
				++insertedCount;
				newPosts.add(post);
			}
		}
		
		if(insertedCount > 0) {
			em.flush();
			cache.flush();
			notificationService.addPostsToNotificationMountQueue(newPosts);
		}

		log.info("GatryWriter: " + insertedCount + " novos posts inseridos.");
	}

}
