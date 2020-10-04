package com.wcipriano.TraderAPI;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TraderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderApiApplication.class, args);
	}

	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		return liquibase;
	}

}
