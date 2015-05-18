package copyleft.by.pc.jobs.crawlergatry;

import java.util.Calendar;
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
	
	@Resource(name="externalGatryIds")
	private List<String> externalGatryIds; 
	
	@Override
	public Post process(Element el) throws Exception {

		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -30);
		
		String externalCode = el.attr("id"); 
		if(!externalGatryIds.contains(externalCode)) {
			Post post = new Post();
			post.setSourceId(gatryId);
			post.setExternalId(externalCode);
			post.setTitle(el.getElementsByTag("h3").first().text() + " por " + el.getElementsByClass("preco").first().text());
			post.setHtml("<p>" + post.getTitle() + "</p>" + "<img src=\"" +  el.getElementsByClass("lazy").first().attr("data-original") + "\" />");
			post.setUrl("http://gatry.com/promocoes");
			post.setPublicationDate(cal.getTime());
			return post;
		} else {
			log.debug("Post id " + externalCode + " ja incluido.");
		}
		return null;
		
	}
	
}
