package com.interteam.interpet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.interteam.interpet.api"})
public class InterpetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterpetApiApplication.class, args);
	}

}
