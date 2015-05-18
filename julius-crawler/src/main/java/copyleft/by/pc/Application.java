package copyleft.by.pc;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class Application {
	
	private Log log = LogFactory.getLog(Application.class);
	
	private static final String CRAWLER_GATRY_JOB = "crawlerGatryJob";
	private static final String CRAWLER_HARDMOB_JOB = "crawlerHardmobJob";
	private static final String CRAWLER_CDI_JOB = "crawlerCdiJob";
	private static final String PURGE_POSTS_JOB = "purgePostsJob";
	
	private static ConfigurableApplicationContext ctx = null;

	public static void main(String[] args) throws BeansException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException {

        SpringApplication app = new SpringApplication(Application.class);
        app.setWebEnvironment(false);
        ctx = app.run();

	}

	
	//@Scheduled(fixedDelayString="${gatry.runevery}",initialDelay=10000)
	public void runGatryJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException {
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
    	        		
    	Job crawlerGatryJob = ctx.getBean(CRAWLER_GATRY_JOB, Job.class);
    	JobParameters jobParameters = new JobParametersBuilder()
		.addDate("date", new Date())
		.toJobParameters();
    	
    	JobExecution jobExecution = jobLauncher.run(crawlerGatryJob, jobParameters);
    	
    	BatchStatus batchStatus = jobExecution.getStatus();
    	while(batchStatus.isRunning()){
    		Thread.sleep(1000);
    	}
    	JobInstance jobInstance = jobExecution.getJobInstance();
    	log.info(String.format("Job %s instance %s exited with status %s",jobInstance.getJobName(), jobInstance.getId(), jobExecution.getExitStatus().getExitCode()));
	}
	
	
	//@Scheduled(fixedDelayString="${hardmob.runevery}",initialDelay=20000)
	public void runHardmobJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException {
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
    	        		
    	Job crawlerHardmobJob = ctx.getBean(CRAWLER_HARDMOB_JOB, Job.class);
    	JobParameters jobParameters = new JobParametersBuilder()
		.addDate("date", new Date())
		.toJobParameters();
    	
    	JobExecution jobExecution = jobLauncher.run(crawlerHardmobJob, jobParameters);
    	
    	BatchStatus batchStatus = jobExecution.getStatus();
    	while(batchStatus.isRunning()){
    		Thread.sleep(1000);
    	}
    	JobInstance jobInstance = jobExecution.getJobInstance();
    	log.info(String.format("Job %s instance %s exited with status %s",jobInstance.getJobName(), jobInstance.getId(), jobExecution.getExitStatus().getExitCode()));

	}

	@Scheduled(fixedDelayString="${cdi.runevery}",initialDelay=20000)
	public void runCdiJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException {
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
    	        		
    	Job crawlerCdiJob = ctx.getBean(CRAWLER_CDI_JOB, Job.class);
    	JobParameters jobParameters = new JobParametersBuilder()
		.addDate("date", new Date())
		.toJobParameters();
    	
    	JobExecution jobExecution = jobLauncher.run(crawlerCdiJob, jobParameters);
    	
    	BatchStatus batchStatus = jobExecution.getStatus();
    	while(batchStatus.isRunning()){
    		Thread.sleep(1000);
    	}
    	JobInstance jobInstance = jobExecution.getJobInstance();
    	log.info(String.format("Job %s instance %s exited with status %s",jobInstance.getJobName(), jobInstance.getId(), jobExecution.getExitStatus().getExitCode()));

	}

	
	@Scheduled(cron="${purge.cron}")
	public void runPurgePostsJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException {
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

    	Job purgePostsJob = ctx.getBean(PURGE_POSTS_JOB, Job.class);
    	JobParameters jobParameters = new JobParametersBuilder()
		.addDate("date", new Date())
		.toJobParameters();
    	
    	JobExecution jobExecution = jobLauncher.run(purgePostsJob, jobParameters);
    	
    	BatchStatus batchStatus = jobExecution.getStatus();
    	while(batchStatus.isRunning()){
    		Thread.sleep(1000);
    	}
    	JobInstance jobInstance = jobExecution.getJobInstance();
    	log.info(String.format("Job %s instance %s exited with status %s",jobInstance.getJobName(), jobInstance.getId(), jobExecution.getExitStatus().getExitCode()));

	}
 
}