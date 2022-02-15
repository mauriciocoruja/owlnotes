package dev.mauriciocoruja.owlnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class OwlnotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwlnotesApplication.class, args);
	}

}
