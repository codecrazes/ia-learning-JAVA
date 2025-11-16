package br.com.fiap.ia_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class IaLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(IaLearningApplication.class, args);
	}
}
