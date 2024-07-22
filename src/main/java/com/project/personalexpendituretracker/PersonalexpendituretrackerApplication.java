package com.project.personalexpendituretracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonalexpendituretrackerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PersonalexpendituretrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>> Program RUN Succesfully...!");
	}
}
