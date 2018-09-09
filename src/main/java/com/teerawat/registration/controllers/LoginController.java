package com.teerawat.registration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teerawat.registration.model.JwtUser;
import com.teerawat.registration.model.RegisterResMsg;
import com.teerawat.registration.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	
	@PutMapping(path="/user")
	@ResponseBody
	public RegisterResMsg userRegister(@RequestBody JwtUser user) {
		return loginservice.login(user);
	}

}
