package com.teerawat.registration.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="Login")
@Data
public class LoginEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer loginid;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;

}
