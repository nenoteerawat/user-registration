package com.teerawat.registration.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teerawat.registration.db.domains.Role;
import com.teerawat.registration.db.domains.User;
import com.teerawat.registration.db.repositories.RoleRepository;
import com.teerawat.registration.db.repositories.UserRepository;
import com.teerawat.registration.model.RegisterReqMsg;
import com.teerawat.registration.model.RegisterResMsg;
import com.teerawat.registration.model.RegisterResMsg.ResponseBody;
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
	
	public RegisterResMsg register(RegisterReqMsg req) {
		
		User user = new User();
		user.setFirstName(req.getFirstName());
		user.setLastName(req.getLastName());
		user.setAddress(req.getAddress());
		user.setPhone(req.getPhone());
		user.setReferenceCode(registerHelper.genRefCode(user.getPhone()));
		user.setSalary(Integer.parseInt(req.getSalary()));
		user.setClassification(registerHelper.classifyUser(user.getSalary()));
		
		// Return if unclassify user class.
		if(user.getClassification().equals("UnClassify")) return ResponseBody.CannotClassify.getRegisterResMsg();
		
		user.setUsername(req.getUsername());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		User exitUser = userRepository.findByUsername(req.getUsername());
		Role role = roleRepository.findByRoleName("STANDARD_USER");
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		if (null == exitUser) {
			userRepository.save(user);
			RegisterResMsg res = ResponseBody.Success.getRegisterResMsg();
			return res;
		} else {
			return ResponseBody.ExistUsername.getRegisterResMsg();
		}

	}
}
