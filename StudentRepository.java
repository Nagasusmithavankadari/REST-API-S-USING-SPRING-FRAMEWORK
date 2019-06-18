package com.javacodegeeks.example.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javacodegeeks.example.model.Student;

@Repository
public class StudentRepository {
	
	Map<Long, Student> students = new HashMap<>();
	long currentId = 100;
	
	// Return all students
	public Collection<Student> findAll(){
		return students.values();
	}

	// Find the student with this id
	public Optional<Student> findById(Long id) {
		Student student = null;

		if (students.containsKey(id)) student = students.get(id);
		return Optional.ofNullable(student);
	}
		
	// Save a new student	
	public Student save(Student student) {
		student.setId(++currentId);
		students.put(student.getId(), student);
		return student;
	}
	
	// Update the student with this id
	public Optional<Student> update(Student student) {
		Student currentStudent = students.get(student.getId());

		if (currentStudent != null) {
			students.put(student.getId(), student);
			currentStudent = students.get(student.getId());
		}
		return Optional.ofNullable(currentStudent);
	}
	
	// Delete student with this id
	public Optional<Student> delete(Long id) {
		Student currentStudent = students.get(id);

		if (currentStudent != null) {
			students.remove(id);
		}
		return Optional.ofNullable(currentStudent);
	}
}
