package com.ml.gitmanager.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ml.gitmanager.entities.User;
import com.ml.gitmanager.exceptions.GitManagerException;
import com.ml.gitmanager.exceptions.UserNotFoundException;
import com.ml.gitmanager.repositories.GitManagerRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = {"localhost:3000"})
public class UserController {

	@Autowired
	private GitManagerRepository gitManagerRepository;
	
	@GetMapping("/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		return ResponseEntity.ok(gitManagerRepository.save(user));
	}
	
	@GetMapping("/login/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) throws GitManagerException {
		
		return ResponseEntity.ok(gitManagerRepository.findById(id).orElseThrow(()->new UserNotFoundException("User can not be found with id as : "+id)));
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		
		return ResponseEntity.ok(gitManagerRepository.findAll());
	}
}
