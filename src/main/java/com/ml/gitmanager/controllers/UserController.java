package com.ml.gitmanager.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ml.gitmanager.entities.User;
import com.ml.gitmanager.exceptions.GitManagerException;
import com.ml.gitmanager.exceptions.UserNotFoundException;
import com.ml.gitmanager.repositories.UserRepository;
import com.ml.gitmanager.utilities.GitManagerUtilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		entityManager.createNativeQuery("CALL create_user_table_if_not_exists();").executeUpdate();
		
		User u = new User();
		u.setFname(user.getFname());
		u.setLname(user.getLname());
		u.setEmail(user.getEmail());
		u.setUsername(GitManagerUtilities.hashGenerator(user.getUsername()));
		u.setPassword(GitManagerUtilities.hashGenerator(user.getPassword()));
		u.setAccesstoken(GitManagerUtilities.hashGenerator(user.getAccesstoken()));
		
		return ResponseEntity.ok(userRepository.save(u));
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) throws GitManagerException {
		
		return ResponseEntity.ok(userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User can not be found with id as : "+id)));
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		
		return ResponseEntity.ok(userRepository.findAll());
	}
}
