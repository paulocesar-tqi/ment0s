package copyleft.by.pc.jobs.crawlerhardmob;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.common.entities.Post;

@Scope("step")
public class CrawlerHardmobPostReader implements ItemReader<Post> {
	private static final Log log = LogFactory.getLog(CrawlerHardmobPostReader.class);

	@Autowired
	private GenericDao dao;

	private List<Post> list = null;

	public CrawlerHardmobPostReader(String gatryEndpoint) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Host", "www.hardmob.com.br");
		headers.set("Connection", "keep-alive");
		headers.set("Pragma", "no-cache");
		headers.set("Cache-Control", "no-cache");
		headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.set("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		headers.set("Accept-Encoding", "gzip, deflate, sdch");
		headers.set("Accept-Language", "en-US,en;q=0.8,pt-BR;q=0.6,pt;q=0.4");

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
		        HttpClientBuilder.create().build());
		RestTemplate template = new RestTemplate(clientHttpRequestFactory);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<String> response = template.exchange(gatryEndpoint, HttpMethod.GET, entity, String.class);
		String html = response.getBody();
		html = html.substring(html.indexOf("<div id=\"content\">"), html.indexOf("<div id=\"copyright\""));

		Document document = Jsoup.parse(html);
		Elements elements = document.select("li");

		list = new ArrayList<Post>();
		for (Element el : elements) {
			String url = el.child(0).attr("href");
			String id = url.substring(url.indexOf("php/t-")+6, url.indexOf(".html"));
			Post post = new Post();
			post.setTitle(el.text());
			post.setUrl(url);
			post.setExternalId(id);
			list.add(post);

			log.debug("HardmobReader: " + id + " | " + el.text());
		}
        log.info("HardmobReader: " + list.size() + " posts lidos.");
	}

	@Override
	public Post read() {

		Post post = null;
		if (!list.isEmpty()) {
			post = list.remove(0);
			log.debug("reader: " + post.getTitle());
		}

		return post;
	}
	
}
