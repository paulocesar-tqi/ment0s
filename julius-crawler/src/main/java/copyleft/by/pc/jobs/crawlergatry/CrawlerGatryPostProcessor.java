package copyleft.by.pc.jobs.crawlergatry;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
	
	@Resource(name="externalIds")
	private List<String> externalIds; 
	
	@Override
	public Post process(Element el) throws Exception {

		String externalCode = el.attr("id"); 
		if(!externalIds.contains(externalCode)) {
			Post post = new Post();
			post.setSourceId(gatryId);
			post.setExternalId(externalCode);
			post.setTitle(el.text());
			post.setHtml(el.html());
			post.setPublicationDate(new Date());
			return post;	
		} else {
			log.info("Post id " + externalCode + " ja incluido.");
		}
		return null;
		
	}
	
}
