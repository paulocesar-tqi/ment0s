package copyleft.by.pc.jobs.crawlercdi;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Scope;

import copyleft.by.pc.common.entities.Post;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;

@Scope("step")
public class CrawlerCDIPostReader implements ItemReader<Post> {
	private static final Log log = LogFactory.getLog(CrawlerCDIPostReader.class);

	private List<Post> list = null;

	public CrawlerCDIPostReader(String cdiEndpoint, Integer cdiId) {
		
		Facebook facebook = new FacebookFactory().getInstance();
		ResponseList<facebook4j.Post> feed = null;
		try {
			feed = facebook.getFeed("originalcdi");
		} catch (FacebookException e) {
			log.info("CdiReader: Erro ao recuperar os posts do facebook", e);
		}
		
		list = new ArrayList<Post>();
		for(facebook4j.Post facebookPost : feed) {
			Post post = new Post();
			
			String html = formatLinks(facebookPost.getMessage());
			if(html.length() > 10000) 
				html = html.substring(0, 9995) + "...";
			
			String id = facebookPost.getId();
			
			post.setUrl(cdiEndpoint + id.substring(id.indexOf("_")+1));
			post.setExternalId(facebookPost.getId());
			post.setSourceId(cdiId);
			post.setPublicationDate(facebookPost.getCreatedTime());
			post.setHtml(html);

			list.add(post);

			log.debug("CdiReader: " + post.getId() + " | " + post.getHtml());
		}
        log.info("CdiReader: " + list.size() + " posts lidos.");
	}

	@Override
	public Post read() {

		Post post = null;
		if (!list.isEmpty()) {
			post = list.remove(0);
			log.debug("reader: " + post.getHtml());
		}

		return post;
	}
	
	private String formatLinks(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text, " \t\n\r\f,!()[]'",true);
		StringBuilder newHtml = new StringBuilder();
		while(tokenizer.hasMoreElements()) {
			String word = tokenizer.nextToken();
			if(word.startsWith("http")) {
				word = "<a href=\"javascript:openUrlViewer('"+ word + "')\">[LINK]</a>";
			}
			newHtml.append(word);
		}
		text = newHtml.toString().replaceAll("\\r?\\n", "<br/> ");
		
		return text;
	}
	
	public static void main(String[] args) {
		Document document = Jsoup.parseBodyFragment("#Netshoes\nTênis Adidas Cosmic Freeze\nR$ 118,00 parcelados e com frete grátis!\nhttp://bit.ly/1IIJgDW");
		document.outputSettings().escapeMode(EscapeMode.extended);
		Element tag = document.getElementsByTag("body").first();
		System.out.println(tag.html());
	}
	
}
