package com.teerawat.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teerawat.registration.db.entities.UserEntity;
import com.teerawat.registration.db.repositories.UserRepository;

@Service
public class GetUserInfoService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Iterable<UserEntity> getAllUserInfo(){
		return userRepository.findAll();
	}
	
}
