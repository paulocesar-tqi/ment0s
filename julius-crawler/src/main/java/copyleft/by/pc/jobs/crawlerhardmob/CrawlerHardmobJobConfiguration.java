package copyleft.by.pc.jobs.crawlerhardmob;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.configuration.InfrastructureConfiguration;
import copyleft.by.pc.configuration.ServicesConfiguration;
import copyleft.by.pc.listeners.ProtocolListener;

@Configuration
@EnableBatchProcessing
@Import({InfrastructureConfiguration.class, ServicesConfiguration.class})
public class CrawlerHardmobJobConfiguration {

	@Autowired
	private JobBuilderFactory jobs;
 
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
		
	@Autowired
	private GenericDao dao;
	
	@Autowired
	@Value("${hardmob.endpoint}") 
	private String hardmobEndpoint;
	 
	@Autowired
	@Value("${hardmob.threads}") 
	private Integer hardmobThreads;
	
	@Autowired
	@Value("${hardmob.id}") 
	private Integer hardmobId;
	 
	
	@Bean
	public Job crawlerHardmobJob(){
		return jobs.get("crawlerHardmobJob")
//				.listener(protocolListener())
				.start(crawlerHardmobStep1())
				.build();
	}
	
	@Bean
	public TaskExecutor hardmobAsyncTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(hardmobThreads);
		taskExecutor.setCorePoolSize(hardmobThreads);
		taskExecutor.setQueueCapacity(hardmobThreads * 1000);
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.setThreadNamePrefix("AsyncHardmob-");
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
	
	@Bean
	public Step crawlerHardmobStep1(){
		return stepBuilderFactory.get("crawlerHardmobStep1")
				.<Post,Future<Post>>chunk(100)
				.reader(readerHardmob())
				.processor(asyncItemProcessorHardmob())
				.writer(asyncItemWriterHardmob())
//				.listener(logProcessListener())
				.faultTolerant()
				.skipLimit(10) //default is set to 0
				.skip(MySQLIntegrityConstraintViolationException.class)
				.build();
	}

	@StepScope
	@Bean
	public ItemReader<Post> readerHardmob(){
		CrawlerHardmobPostReader reader = new CrawlerHardmobPostReader(hardmobEndpoint);
		return reader;
	}

	@StepScope
    @Bean
    public ItemProcessor<Post, Future<Post>> asyncItemProcessorHardmob() {
		AsyncItemProcessor<Post, Post> asyncItemProcessor = new AsyncItemProcessor<Post, Post>();
		asyncItemProcessor.setDelegate(processorHardmob());
        asyncItemProcessor.setTaskExecutor(hardmobAsyncTaskExecutor());
        return asyncItemProcessor;
    }

	@StepScope
    @Bean
    public ItemProcessor<Post, Post> processorHardmob() {
        return new CrawlerHardmobPostProcessor();
    }
	
	@StepScope
    @Bean
    public ItemWriter<Future<Post>> asyncItemWriterHardmob() {
		AsyncItemWriter<Post> asyncItemWriter = new AsyncItemWriter<Post>();
        asyncItemWriter.setDelegate(writerHardmob());
        return asyncItemWriter;
    }

	@StepScope
    @Bean
    public ItemWriter<Post> writerHardmob() {
    	return new CrawlerHardmobPostWriter();
    }
    
	@Bean
	public ProtocolListener protocolListener(){
		return new ProtocolListener();
	}
	
	@StepScope
	@Bean
	public List<String> externalHardmobIds(){
		return dao.getExternalIdsBySource(hardmobId);
	}

}
