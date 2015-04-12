package copyleft.by.pc.jobs.crawlergatry;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Scope("step")
public class CrawlerGatryPostReader implements ItemReader<String> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostReader.class);

	private List<String> list = null;
	
	public CrawlerGatryPostReader() {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity("http://www.intensedebate.com/js/wordpressTemplateCommentWrapper2.php?acct=152aad78d7b44d72a43402870c2d1c89&postid=http://gatry.com/comprei/&title=Gatry%2520-%2520Promo%25C3%25A7%25C3%25B5es&url=http://gatry.com/comprei/", String.class);
		String html = entity.getBody();
		html = html.replaceAll("\\\\", "");
		html = html.substring(html.indexOf("<div id=\"idc-cover\" class=\"idc-comments\">"), html.indexOf("<div id=\"IDCommentsNewThreadCover\""));
       
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div[class=idc-c-t-inner]");
        
        list = new ArrayList<String>();
        for(Element el : elements) {
    		list.add(el.text());

    		log.info("readLoader: " + el.attr("id") + " | " + el.text());
        }
	}

	@Override
	public String read() {
		
		String html = null;
		if (!list.isEmpty()) {
			html = list.remove(0);
		}
		log.info("reader: " + html);
		
		return html;
	}


}
