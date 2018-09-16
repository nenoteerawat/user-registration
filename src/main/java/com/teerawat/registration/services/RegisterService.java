package com.teerawat.registration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teerawat.registration.db.domains.Role;
import com.teerawat.registration.db.domains.User;
import com.teerawat.registration.db.repositories.RoleRepository;
import com.teerawat.registration.db.repositories.UserRepository;
import com.teerawat.registration.models.GetUserInfo;
import com.teerawat.registration.models.NormalResponseModel;
import com.teerawat.registration.models.NormalResponseModel.ResponseBody;
import com.teerawat.registration.services.components.RegisterHelper;

@Service
public class RegisterService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RegisterHelper registerHelper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public NormalResponseModel register(GetUserInfo getUserInfo) {
		User user = getUserInfo.getUser();
		user.setReferenceCode(registerHelper.genRefCode(user.getPhone()));
		user.setClassification(registerHelper.classifyUser(user.getSalary()));
		
		// Return if unclassify user class.
		if(user.getClassification().equals("UnClassify")) return ResponseBody.CannotClassify.getRegisterResMsg();
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		getUserInfo.getRole().forEach(roleName -> {
			Role role = roleRepository.findByRoleName(roleName);
			roles.add(role);
		});
		user.setRoles(roles);
		User exitUser = userRepository.findByUsername(user.getUsername());
		if (null == exitUser) {
			userRepository.save(user);
			NormalResponseModel res = ResponseBody.Success.getRegisterResMsg();
			return res;
		} else {
			return ResponseBody.ExistUsername.getRegisterResMsg();
		}

	}
}
