package copyleft.by.pc.jobs.crawlergatry;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.jsoup.nodes.Element;
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

import copyleft.by.pc.common.configuration.InfrastructureConfiguration;
import copyleft.by.pc.common.configuration.ServicesConfiguration;
import copyleft.by.pc.common.dao.GenericDao;
import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.common.listeners.LogProcessListener;
import copyleft.by.pc.common.listeners.ProtocolListener;

@Configuration
@EnableBatchProcessing
@Import({InfrastructureConfiguration.class, ServicesConfiguration.class})
public class CrawlerGatryJobConfiguration {

	@Autowired
	private JobBuilderFactory jobs;
 
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
		
	@Autowired
	private GenericDao dao;
	
	@Autowired
	@Value("${gatry.endpoint}") 
	private String gatryEndpoint;
	 
	@Autowired
	@Value("${gatry.threads}") 
	private Integer gatryThreads;
	
	@Autowired
	@Value("${gatry.id}") 
	private Integer gatryId;
	 
	
	@Bean
	public Job crawlerGatryJob(){
		return jobs.get("crawlerGatryJob")
//				.listener(protocolListener())
				.start(crawlerGatryStep1())
				.build();
	}
	
	@Bean
	public TaskExecutor gatryAsyncTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(gatryThreads);
		taskExecutor.setCorePoolSize(gatryThreads);
		taskExecutor.setQueueCapacity(gatryThreads * 1000);
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.setThreadNamePrefix("AsyncGatry-");
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
	
	@Bean
	public Step crawlerGatryStep1(){
		return stepBuilderFactory.get("crawlerGatryStep1")
				.<Element,Future<Post>>chunk(100)
				.reader(readerGatry())
				.processor(asyncItemProcessorGatry())
				.writer(asyncItemWriterGatry())
//				.listener(logProcessListener())
				.faultTolerant()
				.skipLimit(10) //default is set to 0
				.skip(MySQLIntegrityConstraintViolationException.class)
				.build();
	}

	@StepScope
	@Bean
	public ItemReader<Element> readerGatry(){
		CrawlerGatryPostReader reader = new CrawlerGatryPostReader(gatryEndpoint);
		return reader;
	}

	@StepScope
    @Bean
    public ItemProcessor<Element, Future<Post>> asyncItemProcessorGatry() {
		AsyncItemProcessor<Element, Post> asyncItemProcessor = new AsyncItemProcessor<Element, Post>();
		asyncItemProcessor.setDelegate(processorGatry());
        asyncItemProcessor.setTaskExecutor(gatryAsyncTaskExecutor());
        return asyncItemProcessor;
    }

	@StepScope
    @Bean
    public ItemProcessor<Element, Post> processorGatry() {
        return new CrawlerGatryPostProcessor();
    }
	
	@StepScope
    @Bean
    public ItemWriter<Future<Post>> asyncItemWriterGatry() {
		AsyncItemWriter<Post> asyncItemWriter = new AsyncItemWriter<Post>();
        asyncItemWriter.setDelegate(writerGatry());
        return asyncItemWriter;
    }

	@StepScope
    @Bean
    public ItemWriter<Post> writerGatry() {
    	return new CrawlerGatryPostWriter();
    }
    
	@Bean
	public ProtocolListener protocolListener(){
		return new ProtocolListener();
	}
 
	@Bean
	public LogProcessListener logProcessListener(){
		return new LogProcessListener();
	}
	
	@StepScope
	@Bean
	public List<String> externalGatryIds(){
		return dao.getExternalIdsBySource(gatryId);
	}

}
