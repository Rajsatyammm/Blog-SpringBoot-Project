package com.satyam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogProjectApplication.class, args);
	}

	@Bean
	public static ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
