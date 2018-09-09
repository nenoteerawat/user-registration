package com.teerawat.registration.model;

import lombok.Data;

@Data
public class RegisterReqMsg {
	private String name;
	private String address;
	private String phone;
	private String username;
	private String password;
	private String salary;
}
