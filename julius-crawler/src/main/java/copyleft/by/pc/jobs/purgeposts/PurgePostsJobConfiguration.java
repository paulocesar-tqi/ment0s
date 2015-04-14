package copyleft.by.pc.jobs.purgeposts;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import copyleft.by.pc.common.configuration.InfrastructureConfiguration;
import copyleft.by.pc.common.configuration.ServicesConfiguration;

@Configuration
@EnableBatchProcessing
@Import({InfrastructureConfiguration.class, ServicesConfiguration.class})
public class PurgePostsJobConfiguration {

	@Autowired
	private JobBuilderFactory jobs;
 
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
		
	@Bean
	public Job purgePostsJob(){
		return jobs.get("purgePostsJob")
//				.listener(protocolListener())
				.start(purgePostsStep1())
				.build();
	}
	
	@Bean
	public Step purgePostsStep1(){
		return stepBuilderFactory.get("purgePostsStep1")
				.tasklet(tasklet())
//				.listener(logProcessListener())
				.build();
	}

	@StepScope
	@Bean
	public Tasklet tasklet(){
		return new PurgePostsTasklet();
	}

}
