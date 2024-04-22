package com.ml.gitmanager.controllers;

import java.util.List;
import java.util.Optional;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ml.gitmanager.entities.User;
import com.ml.gitmanager.exceptions.GitManagerException;
import com.ml.gitmanager.exceptions.UserNotFoundException;
import com.ml.gitmanager.repositories.UserRepository;
import com.ml.gitmanager.utilities.GitManagerUtilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = { "http://localhost:3000" })
public class UserController {

	private QueryByExampleExecutor<User> queryByExampleExecutor;

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// calling stored procedure named 'create_user_table_if_not_exists()' just to
		// ensure, table is created before the record is inserted.
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

	@Transactional
	@GetMapping("/users/id/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) throws GitManagerException {

		return ResponseEntity.ok(userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User can not be found with id as : " + id)));
	}

	@Transactional
	@GetMapping("/users/{username}")
	@org.springframework.data.jpa.repository.Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
	public ResponseEntity<User> getUser(@PathVariable("username") String username) throws GitManagerException {

		// User user = new User();
		// user.setUsername(username);
		// Example<User> example = Example.of(user);

		return ResponseEntity.ok(userRepository.findByUsername(GitManagerUtilities.hashGenerator(username))
				.orElseThrow(() -> new UserNotFoundException("User can not be found with username : " + username)));
	}

	@Transactional
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {

		return ResponseEntity.ok(userRepository.findAll());
	}

	@Transactional
	@PatchMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws GitManagerException {
		Optional<User> optionalUser = userRepository
				.findByUsername(GitManagerUtilities.hashGenerator(user.getUsername()));
		if (optionalUser.isPresent()) {
			User existingRecords = optionalUser.get();
			existingRecords.setFname(user.getFname());
			existingRecords.setLname(user.getLname());
			existingRecords.setEmail(user.getEmail());
			existingRecords.setUsername(GitManagerUtilities.hashGenerator(user.getUsername()));
			existingRecords.setPassword(GitManagerUtilities.hashGenerator(user.getPassword()));
			existingRecords.setAccesstoken(GitManagerUtilities.hashGenerator(user.getAccesstoken()));
			existingRecords.setDob(user.getDob());
			existingRecords.setAge(user.getAge());
			existingRecords.setGender(user.getGender());
			existingRecords.setPhone(user.getPhone());

			User updatedUser = null;
			if (userRepository.existsById(existingRecords.getId())) {
				updatedUser = userRepository.save(existingRecords);
			}
			return ResponseEntity.ok(updatedUser);
		} else {
			throw new UserNotFoundException("User not found with username: " + user.getUsername());
		}
	}

}
