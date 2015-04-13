package copyleft.by.pc.jobs.crawlergatry;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import copyleft.by.pc.common.entities.Post;

public class CrawlerGatryPostWriter implements ItemWriter<Post> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostWriter.class);
	
	@PersistenceContext
	@Autowired
	private EntityManager em;
	
	@Override
	public void write(List<? extends Post> posts) throws Exception {
		boolean hasSaved=false;
		for(Post post : posts) {
			try {
				em.persist(post);
				hasSaved = true;
			} catch (Exception e) {
				log.info("Não salvou o post: " + post.getExternalId() + " por causa de: " + e.getMessage());
			}
			
			if(hasSaved)
				em.flush();
			//log.info("writer: " + post.getExternalId());
		}
	}
	
}
