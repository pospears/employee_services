package com.array.access.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "userId", nullable = false)
	private long userId;
	
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "bUserLoggedIn", nullable = false)
	private boolean bUserLoggedIn = false;
	
	public User() {}

	public User(String email, String password) {
	    this.email = email;
	    this.password = password;
	  }

	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public boolean getUserLoggedIn() {
		return bUserLoggedIn;
	}

	public void setUserLoggedIn(boolean bLoggedIn) {
		this.bUserLoggedIn = bLoggedIn;
	}
	
	public boolean validUserAccount() {
		return this.email != null && this.password != null && this.email != "" && this.password != "";
	}


	@Override
	public String toString() {
		return String.format("User[id=%d, email='%s', loggedIn=%b]", userId, email, bUserLoggedIn);
	}
}
