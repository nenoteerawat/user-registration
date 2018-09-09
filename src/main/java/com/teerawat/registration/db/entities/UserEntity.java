package com.teerawat.registration.db.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="User")
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	
	@NotNull
	private String name;
	
	@NotNull
	private String address;
	
	@NotNull
	private String phone;
	
	@NotNull
	private String reference;
	
	@NotNull
	private Integer salary;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loginid")
	private LoginEntity loginid; 

}
