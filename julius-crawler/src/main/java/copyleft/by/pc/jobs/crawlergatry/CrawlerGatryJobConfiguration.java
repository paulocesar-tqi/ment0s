package copyleft.by.pc.jobs.crawlergatry;

import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
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
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import copyleft.by.pc.common.configuration.InfrastructureConfiguration;
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
	Environment env;
	 
	
	@Bean
	public Job crawlerGatryJob(){
		return jobs.get("crawlerGatryJob")
//				.listener(protocolListener())
				.start(step())
				.build();
	}
	
//	@StepScope
//	@Bean
//	public Step partitionStep(){
//		return stepBuilderFactory.get("partitionStep")
//				.partitioner(step())
//				.partitioner("step", partitioner())
//				.taskExecutor(gatryTaskExecutor())
//				.build();
//	}
	
	@Bean
	public TaskExecutor gatryAsyncTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(Integer.parseInt(env.getProperty("gatry.threads")));
		taskExecutor.setCorePoolSize(Integer.parseInt(env.getProperty("gatry.threads")));
		taskExecutor.setQueueCapacity(Integer.parseInt(env.getProperty("gatry.threads")) * 1000);
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.setThreadNamePrefix("AsyncExecutor-");
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
	
//	@StepScope
//	@Bean
//	public Partitioner partitioner(){
//		return new CrawlerGatryPartitioner();
//	}
// 
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("step")
				.<String,Future<Post>>chunk(100) //important to be one in this case to commit after every line read
				.reader(reader())
				.processor(asyncItemProcessor())
				.writer(asyncItemWriter())
//				.listener(logProcessListener())
				.faultTolerant()
				.skipLimit(10) //default is set to 0
				.skip(MySQLIntegrityConstraintViolationException.class)
				.build();
	}

	@StepScope
	@Bean
	public ItemReader<String> reader(){
		CrawlerGatryPostReader reader = new CrawlerGatryPostReader();
		return reader; 
	}

	@StepScope
    @Bean
    public ItemProcessor<String, Future<Post>> asyncItemProcessor() {
		AsyncItemProcessor<String, Post> asyncItemProcessor = new AsyncItemProcessor<String, Post>();
		asyncItemProcessor.setDelegate(processor());
        asyncItemProcessor.setTaskExecutor(gatryAsyncTaskExecutor());
        return asyncItemProcessor;
    }

	@StepScope
    @Bean
    public ItemProcessor<String, Post> processor() {
        return new CrawlerGatryPostProcessor();
    }
	
	@StepScope
    @Bean
    public ItemWriter<Future<Post>> asyncItemWriter() {
		AsyncItemWriter<Post> asyncItemWriter = new AsyncItemWriter<Post>();
        asyncItemWriter.setDelegate(writer());
        return asyncItemWriter;
    }

	@StepScope
    @Bean
    public ItemWriter<Post> writer() {
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
}
