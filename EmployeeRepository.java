package com.javacodegeeks.example.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javacodegeeks.example.model.Employee;
import com.sun.el.stream.Optional;

@Repository
public class EmployeeRepository {

	
	Map<Long, Employee> employees = new HashMap<>();
	long currentId = 100;
	
	// Return all students
	public Collection<Employee> findAll(){
		return employees.values();
	}

	// Find the student with this id
	public Optional findById(Long id) {
		Employee employee = null;

		if (employees.containsKey(id)) employee = employees.get(id);
		//return Optional.ofNullable(employee);
		return null;
	}
		
	// Save a new student	
	public Employee save(Employee employee) {
		 employee.setId(++currentId);
		employees.put(employee.getId(), employee);
		return employee;
	}
	// Update the student with this id
	public Optional update(Employee employee) {
		Employee currentEmployee = employees.get(employee.getId());

		if (currentEmployee != null) {
			employees.put(employee.getId(), employee);
			currentEmployee = employees.get(employee.getId());
		}
		//return Optional.ofNullable(currentEmployee);
	}
	
	// Delete student with this id
	public Optional delete(Long id) {
		Employee currentEmployee = employees.get(id);

		if (currentEmployee != null) {
			employees.remove(id);
		}
		//return Optional.ofNullable(currentEmployee);
		return null;
	}}
