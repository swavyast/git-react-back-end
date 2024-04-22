package com.ml.gitmanager.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fname;
	private String lname;
	private String username;
	private String email;
	private String password;
	private String accesstoken;
	private LocalDate dob;
	private Byte age;
	private Gender gender;
	private Long phone;

	public User() {
		// Default Constructor
	}

	public User(String fname, String lname, String username, String email, String password, String accesstoken) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.accesstoken = accesstoken;
	}
	public User(Long id, String fname, String lname, String username, String email, String password, String accesstoken) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.accesstoken = accesstoken;
	}

	public User(LocalDate dob, Byte age, Gender gender, Long phone) {
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username + ", email="
				+ email + ", password=" + password + ", accesstoken=" + accesstoken + ", dob=" + dob + ", age=" + age
				+ ", gender=" + gender + ", phone=" + phone + "]";
	}

}