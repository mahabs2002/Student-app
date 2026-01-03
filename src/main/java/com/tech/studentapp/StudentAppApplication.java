package com.tech.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentAppApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext= SpringApplication.run(StudentAppApplication.class, args);

	}

}
