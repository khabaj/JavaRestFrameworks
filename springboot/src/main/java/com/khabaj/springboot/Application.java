package com.khabaj.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		SpringApplication.run(Application.class, args);
		System.out.println("Started in " + (System.currentTimeMillis() - time) + " ms");
	}
}
