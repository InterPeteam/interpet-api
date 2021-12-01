package com.interteam.interpet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class InterpetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterpetApiApplication.class, args);
	}

}
