package copyleft.by.pc.jobs.crawlercdi;

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

@Configuration
@EnableBatchProcessing
@Import({InfrastructureConfiguration.class, ServicesConfiguration.class})
public class CrawlerCDIJobConfiguration {

	@Autowired
	private JobBuilderFactory jobs;
 
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
		
	@Autowired
	private GenericDao dao;
	
	@Autowired
	@Value("${cdi.endpoint}") 
	private String cdiEndpoint;
	 
	@Autowired
	@Value("${cdi.threads}") 
	private Integer cdiThreads;
	
	@Autowired
	@Value("${cdi.id}") 
	private Integer cdiId;
	 
	
	@Bean
	public Job crawlerCdiJob(){
		return jobs.get("crawlerCdiJob")
//				.listener(protocolListener())
				.start(crawlerCdiStep1())
				.build();
	}
	
	@Bean
	public TaskExecutor cdiAsyncTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(cdiThreads);
		taskExecutor.setCorePoolSize(cdiThreads);
		taskExecutor.setQueueCapacity(cdiThreads * 1000);
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.setThreadNamePrefix("AsyncCdi-");
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
	
	@Bean
	public Step crawlerCdiStep1(){
		return stepBuilderFactory.get("crawlerCdiStep1")
				.<Post,Future<Post>>chunk(100)
				.reader(readerCdi())
				.processor(asyncItemProcessorCdi())
				.writer(asyncItemWriterCdi())
//				.listener(logProcessListener())
				.faultTolerant()
				.skipLimit(10) //default is set to 0
				.skip(MySQLIntegrityConstraintViolationException.class)
				.build();
	}

	@StepScope
	@Bean
	public ItemReader<Post> readerCdi(){
		CrawlerCDIPostReader reader = new CrawlerCDIPostReader(cdiEndpoint, cdiId);
		return reader;
	}

	@StepScope
    @Bean
    public ItemProcessor<Post, Future<Post>> asyncItemProcessorCdi() {
		AsyncItemProcessor<Post, Post> asyncItemProcessor = new AsyncItemProcessor<Post, Post>();
		asyncItemProcessor.setDelegate(processorCdi());
        asyncItemProcessor.setTaskExecutor(cdiAsyncTaskExecutor());
        return asyncItemProcessor;
    }

	@StepScope
    @Bean
    public ItemProcessor<Post, Post> processorCdi() {
        return new CrawlerCDIPostProcessor();
    }
	
	@StepScope
    @Bean
    public ItemWriter<Future<Post>> asyncItemWriterCdi() {
		AsyncItemWriter<Post> asyncItemWriter = new AsyncItemWriter<Post>();
        asyncItemWriter.setDelegate(writerCdi());
        return asyncItemWriter;
    }

	@StepScope
    @Bean
    public ItemWriter<Post> writerCdi() {
    	return new CrawlerCDIPostWriter();
    }
    
	@StepScope
	@Bean
	public List<String> externalCdiIds(){
		return dao.getExternalIdsBySource(cdiId);
	}

}
