package com.teerawat.registration.models;

import java.util.List;

import com.teerawat.registration.db.domains.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NormalViewModel {
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String username;
	private String classification;
	private List<Role> roles;
}
