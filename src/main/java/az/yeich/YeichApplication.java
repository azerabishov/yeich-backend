package az.yeich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class YeichApplication {

//	@PostConstruct
//	public void init(){
//		// Setting Spring Boot SetTimeZone
//		TimeZone.setDefault(TimeZone.getTimeZone("AZT"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(YeichApplication.class, args);
	}

}
