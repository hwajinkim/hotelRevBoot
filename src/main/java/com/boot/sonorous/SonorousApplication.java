package com.boot.sonorous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SonorousApplication {

	public static void main(String[] args) {

		SpringApplication.run(SonorousApplication.class, args);
	}

}
