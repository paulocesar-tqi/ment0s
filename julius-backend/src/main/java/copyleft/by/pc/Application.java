package copyleft.by.pc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class Application {
	
	public static void main(String[] args)  {

        SpringApplication app = new SpringApplication(Application.class);
        app.setWebEnvironment(true);
        app.run();

	}
}