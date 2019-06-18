package com.javacodegeeks.example.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.javacodegeeks.example.model.Student;
import com.javacodegeeks.example.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentRepository repository;

	@Autowired
	public StudentController(StudentRepository repository) {
		this.repository = repository;
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class StudentNotFoundException extends RuntimeException {

		public StudentNotFoundException() {
			super("Student does not exist");
		}
	}
	
	@GetMapping
	Collection<Student> readStudents(){
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	Student readStudent(@PathVariable Long id) {
		return this.repository.findById(id)
				.orElseThrow(StudentNotFoundException::new);
	}
	
	@PostMapping
	ResponseEntity<?> addStudent(@RequestBody Student student){
		Student result = this.repository.save(student);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping
	Student updateStudent(@RequestBody Student student) {
		return this.repository.update(student)
				.orElseThrow(StudentNotFoundException::new);
	}
	
	@DeleteMapping("/{id}")
	void deleteStudent(@PathVariable Long id) {
		this.repository.delete(id)
			.orElseThrow(StudentNotFoundException::new);
	}	
}
