package com.teerawat.registration.models;

import java.util.List;

import com.teerawat.registration.db.domains.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminUserModel implements GetUserInfo{
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String username;
	private String password;
	private String salary;
	private List<String> roles;
	
	@Override
	public User getUser() throws NumberFormatException{
		User user = User.builder()
					.firstName(firstName)
					.lastName(lastName)
					.address(address)
					.phone(phone)
					.username(username)
					.password(password)
					.salary(Integer.parseInt(salary)).build();
		return user;
	}

	@Override
	public List<String> getRole() {
		return roles;
	}
	
}
