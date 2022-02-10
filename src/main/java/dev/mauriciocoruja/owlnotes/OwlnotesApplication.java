package dev.mauriciocoruja.owlnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OwlnotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwlnotesApplication.class, args);
	}

}
