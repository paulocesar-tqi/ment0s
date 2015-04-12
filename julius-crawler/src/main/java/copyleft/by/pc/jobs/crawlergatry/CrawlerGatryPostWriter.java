package copyleft.by.pc.jobs.crawlergatry;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

import copyleft.by.pc.common.entities.Post;

public class CrawlerGatryPostWriter implements ItemWriter<Post> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostWriter.class);
	
	@Override
	public void write(List<? extends Post> posts) throws Exception {
		for(Post post : posts) {
			log.info("writer: " + post.getHtml());
		}
	}
	
}
