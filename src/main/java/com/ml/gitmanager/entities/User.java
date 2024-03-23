package com.ml.gitmanager.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Setter(value = AccessLevel.NONE)
	private Long id;
	private String name;
	private String username;
	private String email;
	private String password;

	public User(String name, String username, String email, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
