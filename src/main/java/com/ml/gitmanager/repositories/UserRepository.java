package com.ml.gitmanager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ml.gitmanager.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/*
	 * public Boolean createUser(User user);
	 * 
	 * public User getUserById(Long id);
	 * 
	 * public User getUserByName(String name);
	 * 
	 * public User getUserByEmail(String email);
	 * 
	 * public List<User> getAllUsers();
	 * 
	 * public User updateUser(User user);
	 * 
	 * public void deleteUser(Long id);
	 */

	 public Optional<User> findByUsername(String username);
}
