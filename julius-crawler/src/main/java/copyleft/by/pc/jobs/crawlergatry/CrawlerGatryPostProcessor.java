package copyleft.by.pc.jobs.crawlergatry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import copyleft.by.pc.common.entities.Post;

public class CrawlerGatryPostProcessor implements ItemProcessor<Post,Post> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostProcessor.class);
	
	@Override
	public Post process(Post post) throws Exception {
		log.info("process: " + post.getHtml());
		return post;
	}
	
}
