package br.com.plannermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PlannermanagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlannermanagerApplication.class, args);
	}

}
