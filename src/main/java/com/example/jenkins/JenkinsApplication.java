package com.example.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class JenkinsApplication {
	@GetMapping("/")
	public String message(){
		return "Hello Merikid";
	}

	public static void main(String[] args) {
		SpringApplication.run(JenkinsApplication.class, args);
	}

}
