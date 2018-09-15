package com.teerawat.registration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teerawat.registration.model.RegisterReqMsg;
import com.teerawat.registration.model.RegisterResMsg;
import com.teerawat.registration.services.RegisterService;

@RestController
@RequestMapping("/regis")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	@PutMapping(path="/user")
	@ResponseBody
	public RegisterResMsg userRegister(@RequestBody RegisterReqMsg req) {
		return service.register(req);
	}
	
	@PutMapping(path="/useradmin")
	@ResponseBody
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public RegisterResMsg userAdminRegister(@RequestBody RegisterReqMsg req) {
		return service.register(req);
	}
	
}
