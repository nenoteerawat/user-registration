package com.teerawat.registration.model;

import com.teerawat.registration.db.entities.LoginEntity;

import lombok.Data;

@Data
public class JwtUser {
	private String username;
	private String password;
	
	public JwtUser(LoginEntity login) {
		this.username = login.getUsername();
		this.password = login.getPassword();
	}
	public JwtUser() {
		
	}
}
