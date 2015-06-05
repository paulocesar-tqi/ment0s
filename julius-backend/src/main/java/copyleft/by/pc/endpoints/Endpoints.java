package copyleft.by.pc.endpoints;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.common.entities.User;
import copyleft.by.pc.utils.AesUtil;

@Controller
@Configuration
public class Endpoints {
	
	private static final Log log = LogFactory.getLog(Endpoints.class);

	//Properties for encryptation
	private final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
	private final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
	private final int KEY_SIZE = 128;
	private final int ITERATION_COUNT = 10;
	private final String PASSPHRASE = "i wanna be sedated";
	
	@Autowired
	@Value("${post.page.size}") 
	private Integer postPageSize;
	
	@Autowired
	private GenericDao dao;
	
	@Autowired
	private MemcachedClient cache;
	
	private final Queue<User> updateUserQueue = new ConcurrentLinkedQueue<User>();
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> registerDevice(
			@RequestParam(value = "regId", required = true) String value,
			@RequestParam(value = "platform", required = false, defaultValue = "android") String type) {
		
		dao.createOrUpdateUser(value, type);
		log.info("User " + value + " | " + type + " processado.");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> listPosts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
		
		String json = (String) cache.get("page"+pageNumber);
		if(json == null) {
			log.info("Pagina nao existe no cache, recuperando...");
			List<Post> posts = dao.getPostsByFilter(pageNumber, postPageSize);
			
			ObjectMapper mapper = new ObjectMapper();
	        json = "";
	        try {
				json = mapper.writeValueAsString(posts);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
	        log.debug("Antes: " + json);
	        json = encrypt(json);
			log.debug(json);
			cache.set("page"+pageNumber, 0, json);
		} else {
			log.info("Pagina retornada do memcache");
		}
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<Post> getPostById(
			@RequestParam(value = "id", required = true) Long id) {
		
		Post post = dao.getPostById(id);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuserconfig", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> getUserById(
			@RequestParam(value = "id", required = true) String id) {
		
		User user = dao.getUserById(id);
		
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
			json = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        log.info("Antes: " + json);
        json = encrypt(json);
		log.info(json);
		
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveuserconfig", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> saveUserConfig(
			@RequestParam(value = "payload", required = true) String payload) {
		
		String json = decrypt(payload);
		ObjectMapper mapper = new ObjectMapper();
        try {
        	User user = mapper.readValue(json, User.class);
        	this.updateUserQueue.add(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	private String encrypt(String input) {
		AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
        return util.encrypt(SALT, IV, PASSPHRASE, input);
	}

	private String decrypt(String input) {
		AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
        return util.decrypt(SALT, IV, PASSPHRASE, input);
	}

	
	@Scheduled(fixedDelay=5000)
	public void processQueues() {
		for (User user : this.updateUserQueue) {
			dao.updateUser(user);
			this.updateUserQueue.remove(user);
			log.info("User " + user.getRegId() + " atualizado.");
		}
	}
}
