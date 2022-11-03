package com.argano.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class BasicAuthDemoMaven2Application {

	public static void main(String[] args) {
		SpringApplication.run(BasicAuthDemoMaven2Application.class, args);
	}

}
