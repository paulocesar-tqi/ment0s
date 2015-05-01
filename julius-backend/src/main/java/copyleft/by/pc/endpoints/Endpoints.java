package copyleft.by.pc.endpoints;

import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;
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
	
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> listPosts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
		
		List<Post> posts = dao.getPostsByFilter(pageNumber, postPageSize);
		
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
			json = mapper.writeValueAsString(posts);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//ResponseEntity<List<Post>> oi = new ResponseEntity<>(posts, HttpStatus.OK);
        log.info("Antes: " + json);
        json = encrypt(json);
		log.info(json);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<Post> getPostById(
			@RequestParam(value = "id", required = true) Long id) {
		
		Post post = dao.getPostById(id);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	
	private String encrypt(String input) {
		Key key = new AesKey("CHAVE_DE_16_BITS".getBytes());
		log.info(key);
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(input);
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(key);
		String serializedJwe = "";
		try {
			serializedJwe = jwe.getCompactSerialization();
		} catch (JoseException e) {
			e.printStackTrace();
		}
		return serializedJwe;

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
