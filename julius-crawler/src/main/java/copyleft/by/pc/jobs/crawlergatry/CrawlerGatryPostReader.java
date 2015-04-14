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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import copyleft.by.pc.common.dao.GenericDao;

@Scope("step")
public class CrawlerGatryPostReader implements ItemReader<Element> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostReader.class);

	@Autowired
	private GenericDao dao;
	
	private List<Element> list = null;
	
	public CrawlerGatryPostReader(String gatryEndpoint) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity(gatryEndpoint, String.class);
		String html = entity.getBody();
		html = html.replaceAll("\\\\", "");
		html = html.substring(html.indexOf("<div id=\"idc-cover\" class=\"idc-comments\">"), html.indexOf("<div id=\"IDCommentsNewThreadCover\""));
       
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div[class=idc-c-t-inner]");
        
        list = new ArrayList<Element>();
        for(Element el : elements) {
    		list.add(el);

    		log.info("readLoader: " + el.attr("id") + " | " + el.text());
        }
	}

	@Override
	public Element read() {
		
		Element html = null;
		if (!list.isEmpty()) {
			html = list.remove(0);
		}
		//log.info("reader: " + html);
		
		return html;
	}


}
