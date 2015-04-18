package copyleft.by.pc.endpoints;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import copyleft.by.pc.common.dao.GenericDao;

@Controller
@Configuration
public class Endpoints {
	
	private static final Log log = LogFactory.getLog(Endpoints.class);
	
	@Autowired
	private GenericDao dao;
	
	private final Queue<String> registrationQueue = new ConcurrentLinkedQueue<String>();

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<String> showOptions(
			@RequestParam(value = "regId", required = true) String value) {
		
		DeferredResult<String> result = new DeferredResult<String>();
		this.registrationQueue.add(value);
		result.setResult("ok");
		return result;
	}
	
	
	@Scheduled(fixedDelay=5000)
	public void processQueues() {
		for (String id : this.registrationQueue) {
			dao.createUser(id);
			this.registrationQueue.remove(id);
			log.info("User " + id + " processado.");
		}
	}
}
