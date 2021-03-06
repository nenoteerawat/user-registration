package com.teerawat.registration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teerawat.registration.db.domains.User;
import com.teerawat.registration.models.AdminUserModel;
import com.teerawat.registration.models.NormalResponseModel;
import com.teerawat.registration.models.NormalUserModel;
import com.teerawat.registration.services.RegisterService;

@RestController
@RequestMapping("/regis")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	@PutMapping(path="/user")
	@ResponseBody
	public NormalResponseModel userRegister(@RequestBody NormalUserModel req) {
		User registedUser = service.register(req);
		if(null == registedUser) {
			return NormalResponseModel.ResponseBody.ExistUsername.getRegisterResMsg();
		} else if(registedUser.getClassification().equals("UnClassify")) {
			return NormalResponseModel.ResponseBody.CannotClassify.getRegisterResMsg();
		} else {
			return NormalResponseModel.ResponseBody.Success.getRegisterResMsg();
		}
	}
	
	@PutMapping(path="/useradmin")
	@ResponseBody
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public NormalResponseModel userAdminRegister(@RequestBody AdminUserModel req) {
		User registedUser = service.register(req);
		if(null == registedUser) {
			return NormalResponseModel.ResponseBody.ExistUsername.getRegisterResMsg();
		} else if(registedUser.getClassification().equals("UnClassify")) {
			return NormalResponseModel.ResponseBody.CannotClassify.getRegisterResMsg();
		} else {
			return NormalResponseModel.ResponseBody.Success.getRegisterResMsg();
		}
	}
	
}
