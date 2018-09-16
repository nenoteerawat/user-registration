package com.teerawat.registration.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teerawat.registration.db.domains.User;
import com.teerawat.registration.models.NormalViewModel;
import com.teerawat.registration.services.RetrieveService;

@RestController
@RequestMapping("/retrieve")
public class RetrieveController {
	
	@Autowired
	private RetrieveService retrieveService;
	
	@GetMapping
	@RequestMapping(path="/users")
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<User> getAllUser() {
		return (List<User>) retrieveService.getAllUserInfo();
	}
	
	@GetMapping
	@RequestMapping(path="/normalusers")
	@PreAuthorize("hasAuthority('STANDARD_USER')")
	public List<NormalViewModel> getAllNormalUsers(){
		return (List<NormalViewModel>) retrieveService.getAllNormalUserInfo();
	}
}
