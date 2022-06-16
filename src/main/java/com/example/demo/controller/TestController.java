package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import exception.ResourceNotFoundException;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
		
	}
		// get all employees
		@GetMapping("/employees")
		public List<User> getAllEmployees(){
			return userRepository.findAll();
		}	
		
		// create employee rest api
		@PostMapping("/employees")
		public User createEmployee(@RequestBody User employee) {
			return userRepository.save(employee);
		}
		
		// get employee by id rest api
		@GetMapping("/employees/{id}")
		public ResponseEntity<User> getEmployeeById(@PathVariable Long id) {
			User employee = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			return ResponseEntity.ok(employee);
		}
		

		// update employee rest api
		
		@PutMapping("/employees/{id}")
		public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody User employeeDetails){
			User employee = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			employee.setUsername(employeeDetails.getUsername());
			employee.setEmail(employeeDetails.getEmail());
			
			
			employee.setDep(employeeDetails.getDep());
			employee.setPassword(employeeDetails.getPassword());

			
			User updatedEmployee = userRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		}
		// delete employee rest api
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			User employee = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			userRepository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		
}