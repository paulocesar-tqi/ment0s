package copyleft.by.pc.jobs.crawlergatry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import copyleft.by.pc.common.entities.Post;

public class CrawlerGatryPostProcessor implements ItemProcessor<String,Post> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostProcessor.class);
	
	@Override
	public Post process(String html) throws Exception {
		log.info("process: " + html);
		Thread.sleep(1000 * 5);
		Post post = new Post();
		post.setHtml(html);
		return post;
	}
	
}
