package copyleft.by.pc.configuration;

import java.io.IOException;

import javax.jms.ConnectionFactory;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import com.google.android.gcm.server.Sender;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.services.NotificationService;

@Configuration
public class ServicesConfiguration {

	@Autowired
	@Value("${notification.android.key}") 
	private String notificationGoogleKey;

	
	@Bean
	public GenericDao genericDao(){
		return new GenericDao();
	}
	
	@Bean
	public Sender gcmSender(){
		return new Sender(notificationGoogleKey);
	}
	
	@Bean
	public NotificationService notificationService(){
		return new NotificationService();
	}
	
	@Bean
	public MemcachedClient memcachedClient() throws IOException {
		return new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11111"));
	}
	
	@Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
	
	/*
	@Bean
	public SyndFeedService syndFeedService(){
		return new SyndFeedServiceImpl();
	}
	
	@Bean
	public PodcastAndEpisodeAttributesService podcastAndEpisodeAttributesService(){
		return new PodcastAndEpisodeAttributesServiceImpl();
	}
	
	@Bean
	public EmailNotificationService emailNotificationService1(){
		return new EmailNotificationServiceImpl();
	}
	
	@Bean
	public SocialMediaService socialMediaService(){
		return new SocialMediaServiceImpl();
	}
	
	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(){
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(100);
		
		return poolingHttpClientConnectionManager;
	}
	
	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException{
	    VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
	    Properties props = new Properties();
	    props.put("resource.loader", "class");
	    props.put("class.resource.loader.class", 
	              "org.apache.velocity.runtime.resource.loader." + 
	              "ClasspathResourceLoader");
	    factory.setVelocityProperties(props);
	    
	    return factory.createVelocityEngine();
	}
	*/
}
