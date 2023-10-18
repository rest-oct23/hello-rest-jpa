package com.example.hellorestjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloRestJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloRestJpaApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Ola ke ase";
	}
}
