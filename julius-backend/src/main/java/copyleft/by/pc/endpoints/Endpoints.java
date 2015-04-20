package copyleft.by.pc.endpoints;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.common.entities.Post;

@Controller
@Configuration
public class Endpoints {
	
	private static final Log log = LogFactory.getLog(Endpoints.class);

	@Autowired
	@Value("${post.page.size}") 
	private Integer postPageSize;
	
	@Autowired
	private GenericDao dao;
	
	private final Queue<List<String>> registrationQueue = new ConcurrentLinkedQueue<List<String>>();

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> registerDevice(
			@RequestParam(value = "regId", required = true) String value,
			@RequestParam(value = "platform", required = false, defaultValue = "Android") String type) {
		
		this.registrationQueue.add(Arrays.asList(value,type));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<List<Post>> listPosts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		
		List<Post> posts = dao.getPostsByFilter(pageNumber, postPageSize);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	
	@Scheduled(fixedDelay=5000)
	public void processQueues() {
		for (List<String> list : this.registrationQueue) {
			dao.createOrUpdateUser(list.get(0), list.get(1));
			this.registrationQueue.remove(list);
			log.info("User " + list.get(0) + " | " + list.get(1) + " processado.");
		}
	}
}
