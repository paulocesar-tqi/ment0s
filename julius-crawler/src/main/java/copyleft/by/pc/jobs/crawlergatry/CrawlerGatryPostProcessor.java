package copyleft.by.pc.jobs.crawlergatry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

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
		
		String externalCode = el.attr("id"); 
		if(!externalGatryIds.contains(externalCode)) {
			Post post = new Post();
			
			String html = el.getElementsByTag("h3").first().child(0).text() + " por " + el.getElementsByClass("preco").first().text() + "\n" + el.getElementsByTag("h3").first().child(0).attr("href");
			html = formatLinks(html);
			if(html.length() > 10000) 
				html = html.substring(0, 9995).substring(0,html.lastIndexOf(" ")) + "...";
			
			post.setSourceId(gatryId);
			post.setExternalId(externalCode);
			post.setHtml(html);
			post.setUrl("https://gatry.com/#" + externalCode);
			post.setPublicationDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(el.getElementsByClass("data_postado").first().attr("title").replaceAll(" (.*?) ", " ")));
			return post;
		} else {
			log.debug("Post id " + externalCode + " ja incluido.");
		}
		return null;
		
	}
	
	private String formatLinks(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text, " \t\n\r\f,!()[]'",true);
		StringBuilder newHtml = new StringBuilder();
		while(tokenizer.hasMoreElements()) {
			String word = tokenizer.nextToken();
			if(word.startsWith("http")) {
				word = "<a href=\"javascript:window.open('"+ word + "','_system', 'location=yes')\">" + word + "</a>";
			}
			newHtml.append(word);
		}
		text = newHtml.toString().replaceAll("\\r?\\n", "<br/> ");
		
		return text;
	}
	
}
