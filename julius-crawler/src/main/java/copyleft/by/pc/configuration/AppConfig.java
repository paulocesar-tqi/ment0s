package copyleft.by.pc.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing(modular=true)
public class AppConfig {
    
	/*
    @Bean
    public ApplicationContextFactory crawlerGatryJob(){
    	return new GenericApplicationContextFactory(CrawlerGatryJobConfiguration.class);
    }
    
    @Bean
    public ApplicationContextFactory purgePostsJob(){
    	return new GenericApplicationContextFactory(PurgePostsJobConfiguration.class);
    }
    
    @Bean
    public ApplicationContextFactory crawlerHardmobJob(){
    	return new GenericApplicationContextFactory(CrawlerHardmobJobConfiguration.class);
    }
    
    
    @Bean
    public ApplicationContextFactory newEpisodesNotificationJobs(){
    	return new GenericApplicationContextFactory(NotifySubscribersJobConfiguration.class);
    }    
    */
}
