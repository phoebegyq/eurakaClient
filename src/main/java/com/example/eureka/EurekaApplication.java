package com.example.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableAutoConfiguration
public class EurekaApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}


	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}
