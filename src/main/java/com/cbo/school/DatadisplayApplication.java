package com.cbo.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.cbo")
@EnableJpaRepositories("com.cbo")
@EntityScan("com.cbo")
public class DatadisplayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatadisplayApplication.class, args);
	}
}
