package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BookVaultApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BookVaultApplication.class, args);
	}
}
