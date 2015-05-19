package copyleft.by.pc.jobs.crawlergatry;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import copyleft.by.pc.common.dao.GenericDao;

@Scope("step")
public class CrawlerGatryPostReader implements ItemReader<Element> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostReader.class);

	@Autowired
	private GenericDao dao;

	private List<Element> list = null;

	public CrawlerGatryPostReader(String gatryEndpoint) {

		String html = null;
		try {
			html = IOUtils.toString(Runtime.getRuntime().exec("curl https://gatry.com/").getInputStream());
			
			Document document = Jsoup.parse(html);
			document.outputSettings().escapeMode(EscapeMode.extended);
			Elements elements = document.select("article.promocao");

			list = new ArrayList<Element>();
			for (Element el : elements) {
				list.add(el);

				log.debug("Gatryreader: " + el.attr("id") + " | " + el.text());
			}
			log.info("GatryReader: " + list.size() + " posts lidos.");
			
		} catch (Exception e) {
			log.error("GatryReader: Erro ao recuperar os posts",e);
		}
	}

	@Override
	public Element read() {

		Element html = null;
		if (!list.isEmpty()) {
			html = list.remove(0);
			log.debug("GatryReader: " + html.text());
		}

		return html;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("is: " + IOUtils.toString(Runtime.getRuntime().exec("curl https://gatry.com/").getInputStream()));

	}

	// public static void main(String[] args) {
	// RestTemplate template = new RestTemplate();
	// ResponseEntity<String> entity = template.getForEntity("http://gatry.com",
	// String.class);
	// String html = entity.getBody();
	//
	// Document document = Jsoup.parse(html);
	// Elements elements = document.select("article.promocao");
	//
	// List<Element> list = new ArrayList<Element>();
	// for(Element el : elements) {
	// list.add(el);
	//
	// log.debug("Gatryreader: " + el.attr("id") + " | " + el.text());
	// }
	// }

}
