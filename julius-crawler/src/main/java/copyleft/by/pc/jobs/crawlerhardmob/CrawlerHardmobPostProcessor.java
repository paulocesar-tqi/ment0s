package copyleft.by.pc.jobs.crawlerhardmob;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import copyleft.by.pc.common.entities.Post;

public class CrawlerHardmobPostProcessor implements ItemProcessor<Post,Post> {
	private static final Log log = LogFactory.getLog(CrawlerHardmobPostProcessor.class);
	
	@Autowired
	@Value("${hardmob.id}") 
	private Integer hardmobId;
	
	@Resource(name="externalHardmobIds")
	private List<String> externalHardmobIds; 
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
	
	@Override
	public Post process(Post post) throws Exception {
		
		if(!externalHardmobIds.contains(post.getExternalId())) {
			post.setSourceId(hardmobId);
			post = setOtherFields(post);
			return post;	
		} else {
			log.debug("Post id " + post.getExternalId() + " ja incluido.");
		}
		return null;
		
	}

	private Post setOtherFields(Post post) {
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
		
		try {
			HttpEntity<String> response = template.exchange(post.getUrl(), HttpMethod.GET, entity, String.class);
			String html = response.getBody();
			html = html.substring(html.indexOf("<div id=\"navbar\">"), html.indexOf("<div id=\"copyright\""));

			Document document = Jsoup.parse(html);
			document.outputSettings().escapeMode(EscapeMode.extended);

			Elements elements = document.getElementsByClass("largefont");
			if(elements.size() != 1) {
				log.info("PROBLEMA: Não existe apenas um <p> no topico hardmob: " + post.getUrl());
			}
			else {
				post.setUrl(elements.get(0).child(0).attr("href"));
			}
			
			elements = document.getElementsByClass("posttext");
			if(elements.size() > 0) {
				post.setHtml(elements.get(0).html());
			} else {
				log.info("Não foi possivel setar o html para o topico hardmob: " + post.getUrl());
			}
			
			//Seta a data de publicacao
			elements = document.getElementsByClass("date");
			if(elements.size() > 0) {
				post.setPublicationDate(sdf.parse(elements.get(0).text()));
			} else {
				log.info("Não foi possivel setar a data de publicacao para o topico hardmob: " + post.getUrl());
			}
			
			
		} catch (Exception e) {
			log.info("Nao foi possivel acessar o topico - topico excluido: " + post.getUrl());
			post = null;
		}
		
		return post;
	}
		
}
