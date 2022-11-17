package com.example.goorm;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class GoormApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoormApplication.class, args);
	}
}
