package com.teerawat.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teerawat.registration.db.domains.User;
import com.teerawat.registration.db.repositories.UserRepository;

@Service
public class RetrieveService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Iterable<User> getAllUserInfo(){
		return userRepository.findAll();
	}
	
}
