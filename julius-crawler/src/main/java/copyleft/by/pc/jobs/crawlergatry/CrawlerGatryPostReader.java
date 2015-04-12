package copyleft.by.pc.jobs.crawlergatry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Scope;

import copyleft.by.pc.common.entities.Post;

@Scope("step")
public class CrawlerGatryPostReader implements ItemReader<Post> {
	private static final Log log = LogFactory.getLog(CrawlerGatryPostReader.class);
	
	private String html;
	
	@Override
	public Post read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		log.info("reader: " + html);
		Post post = new Post();
		post.setHtml(html);
		//html = null;
		
		return post;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
