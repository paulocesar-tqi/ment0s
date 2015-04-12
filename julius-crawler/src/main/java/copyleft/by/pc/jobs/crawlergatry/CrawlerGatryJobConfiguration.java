package copyleft.by.pc.jobs.crawlergatry;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
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
				.start(partitionStep())
				.build();
	}
	
	@StepScope
	@Bean
	public Step partitionStep(){
		return stepBuilderFactory.get("partitionStep")
				.partitioner(step())
				.partitioner("step", partitioner())
				.taskExecutor(gatryTaskExecutor())
				.build();
	}
	
	@Bean
	public TaskExecutor gatryTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(Integer.parseInt(env.getProperty("gatry.threads")));
		taskExecutor.setCorePoolSize(Integer.parseInt(env.getProperty("gatry.threads")));
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
	
	@StepScope
	@Bean
	public Partitioner partitioner(){
		return new CrawlerGatryPartitioner();
	}
 
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("step")
				.<Post,Post>chunk(1) //important to be one in this case to commit after every line read
				.reader(reader(null))
				.processor(processor())
				.writer(writer())
//				.listener(logProcessListener())
				.faultTolerant()
				.skipLimit(10) //default is set to 0
				.skip(MySQLIntegrityConstraintViolationException.class)
				.build();
	}

	@StepScope
	@Bean
	public ItemReader<Post> reader(@Value("#{stepExecutionContext['html']}") String html){
		CrawlerGatryPostReader reader = new CrawlerGatryPostReader();
		reader.setHtml(html);
		return reader; 
	}

	@StepScope
    @Bean
    public ItemProcessor<Post, Post> processor() {
        return new CrawlerGatryPostProcessor();
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
