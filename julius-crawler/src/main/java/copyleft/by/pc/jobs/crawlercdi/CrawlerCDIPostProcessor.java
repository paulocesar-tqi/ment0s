package copyleft.by.pc.jobs.crawlercdi;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import copyleft.by.pc.common.entities.Post;

public class CrawlerCDIPostProcessor implements ItemProcessor<Post,Post> {
	private static final Log log = LogFactory.getLog(CrawlerCDIPostProcessor.class);
	
	@Resource(name="externalCdiIds")
	private List<String> externalCdiIds; 
	
	@Override
	public Post process(Post post) throws Exception {

		if(!externalCdiIds.contains(post.getExternalId())) {
			return post;	
		} else {
			log.debug("Post id " + post.getExternalId() + " ja incluido.");
		}
		return null;
	}		
}
