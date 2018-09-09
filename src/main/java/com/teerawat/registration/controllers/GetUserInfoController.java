package com.teerawat.registration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teerawat.registration.db.entities.UserEntity;
import com.teerawat.registration.services.GetUserInfoService;

@RestController
@RequestMapping("/info")
public class GetUserInfoController {
	
	@Autowired
	private GetUserInfoService getUserInfoService;
	
	@GetMapping
	@RequestMapping(path="/users")
	@ResponseBody
	public Iterable<UserEntity> getAllUser() {
		return getUserInfoService.getAllUserInfo();
	}
	
}
