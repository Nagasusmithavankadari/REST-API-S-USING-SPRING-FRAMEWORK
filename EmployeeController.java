package com.javacodegeeks.example.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javacodegeeks.example.model.Employee;
import com.javacodegeeks.example.repository.EmployeeRepository;

@Controller
@RestController
@RequestMapping("/teachers")
public class EmployeeController{

	private final EmployeeRepository repository;

	@Autowired
	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class EmployeeNotFoundException extends RuntimeException {

		public EmployeeNotFoundException() {
			super("Employee does not exist");
		}
	}
	
	@GetMapping
	Collection<Employee> readEmployees(){
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	Employee readEmployee(@PathVariable Long id) {
		return this.repository.findById(id)
				.orElseThrow(EmployeeNotFoundException::new);
	}
	
	@PostMapping
	ResponseEntity<?> addEmployee(@RequestBody Employee employee){
		Employee result = this.repository.save(employee);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping
Employee updateEmployee(@RequestBody Employee employee) {
		return this.repository.update(employee)
				.orElseThrow(EmployeeNotFoundException::new);
	}
	
	@DeleteMapping("/{id}")
	void deleteStudent(@PathVariable Long id) {
		this.repository.delete(id)
			.orElseThrow(EmployeeNotFoundException::new);
	}	
}
