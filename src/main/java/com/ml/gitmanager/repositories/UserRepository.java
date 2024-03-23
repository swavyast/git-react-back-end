package com.ml.gitmanager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ml.gitmanager.entities.User;

public interface UserRepository extends MongoRepository<User, Long> {

	/*
	 * public Boolean createUser(User user);
	 * 
	 * public User getUserById(Long id);
	 * 
	 * public User getUserByName(String name);
	 * 
	 * public User getUserByUsername(String username);
	 * 
	 * public User getUserByEmail(String email);
	 * 
	 * public List<User> getAllUsers();
	 * 
	 * public User updateUser(User user);
	 * 
	 * public void deleteUser(Long id);
	 */
}
