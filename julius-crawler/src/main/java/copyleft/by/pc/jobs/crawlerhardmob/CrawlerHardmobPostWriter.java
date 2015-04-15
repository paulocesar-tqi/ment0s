package copyleft.by.pc.jobs.crawlerhardmob;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import copyleft.by.pc.common.entities.Post;

public class CrawlerHardmobPostWriter implements ItemWriter<Post> {
	private static final Log log = LogFactory.getLog(CrawlerHardmobPostWriter.class);
	
	@PersistenceContext
	@Autowired
	private EntityManager em;
	
	@Override
	public void write(List<? extends Post> posts) throws Exception {
		
		for(Post post : posts) {
			if(post != null) {
				em.persist(post);
			}
		}
		em.flush();
		//log.info("writer: " + post.getExternalId());
	}
	
}
