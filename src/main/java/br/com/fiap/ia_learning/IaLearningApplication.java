package br.com.fiap.ia_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IaLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(IaLearningApplication.class, args);
	}
}
