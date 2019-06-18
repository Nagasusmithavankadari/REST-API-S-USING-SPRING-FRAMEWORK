package com.javacodegeeks.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.javacodegeeks.example.model.Employee;
import com.javacodegeeks.example.repository.EmployeeRepository;

@SpringBootApplication
public class RestApiApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(EmployeeRepository repository) {
		return args -> {
			repository.save(new Employee());
			repository.save(new Employee());
			repository.save(new Employee());
			repository.save(new Employee());
		};
	}

}
