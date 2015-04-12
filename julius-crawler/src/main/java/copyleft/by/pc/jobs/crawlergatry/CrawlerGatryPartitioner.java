package copyleft.by.pc.jobs.crawlergatry;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@StepScope
public class CrawlerGatryPartitioner implements Partitioner {
	private static final Log log = LogFactory.getLog(CrawlerGatryPartitioner.class);
	
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity("http://www.intensedebate.com/js/wordpressTemplateCommentWrapper2.php?acct=152aad78d7b44d72a43402870c2d1c89&postid=http://gatry.com/comprei/&title=Gatry%2520-%2520Promo%25C3%25A7%25C3%25B5es&url=http://gatry.com/comprei/", String.class);
		String html = entity.getBody();
		html = html.replaceAll("\\\\", "");
		html = html.substring(html.indexOf("<div id=\"idc-cover\" class=\"idc-comments\">"), html.indexOf("<div id=\"IDCommentsNewThreadCover\""));
       
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div[class=idc-c-t-inner]");
        
        Map<String, ExecutionContext> partitionMap = new HashMap<String, ExecutionContext>();
        for(Element el : elements) {
    		ExecutionContext context = new ExecutionContext();
    		context.put("html", el.text());
    		partitionMap.put(el.attr("id"), context);

    		log.info("partitioner: " + el.attr("id") + " | " + el.text());
        }
		

		return partitionMap;
	}
	
}
