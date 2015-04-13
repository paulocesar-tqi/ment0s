package copyleft.by.pc.jobs.crawlergatry;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import copyleft.by.pc.common.entities.Post;

public class CrawlerGatryPostProcessor implements ItemProcessor<Element,Post> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostProcessor.class);
	
	@Autowired
	@Value("${gatry.id}") 
	private Integer gatryId;
	
	@Override
	public Post process(Element el) throws Exception {
		//log.info("process: " + el.html());
		//Thread.sleep(1000 * 5);
		Post post = new Post();
		post.setSourceId(gatryId);
		post.setExternalId(el.attr("id"));
		post.setTitle(el.text());
		post.setHtml(el.html());
		post.setPublicationDate(new Date());
		
		return post;
	}
	
}
