package com.teerawat.registration.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teerawat.registration.db.domains.Role;
import com.teerawat.registration.db.domains.User;
import com.teerawat.registration.db.repositories.RoleRepository;
import com.teerawat.registration.db.repositories.UserRepository;
import com.teerawat.registration.models.NormalViewModel;

@Service
public class RetrieveService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	public RetrieveService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public Iterable<User> getAllUserInfo(){
		return userRepository.findAll();
	}

	public List<NormalViewModel> getAllNormalUserInfo() {
		Role adminRole = roleRepository.findByRoleName("ADMIN_USER");
		List<NormalViewModel> normalViews = new ArrayList<>();
		userRepository.findAll().forEach( user -> {
			if(!user.getRoles().contains(adminRole)) {
				NormalViewModel normalView = NormalViewModel.builder()
											 .firstName(user.getFirstName())
											 .lastName(user.getLastName())
											 .address(user.getAddress())
											 .phone(user.getPhone())
											 .username(user.getUsername())
											 .classification(user.getClassification()).build();
				List<Role> roleViews = new ArrayList<>();
				user.getRoles().forEach( role -> {
					roleViews.add(role);
				});
				normalView.setRoles(roleViews);
				normalViews.add(normalView);
			}
		});
		return normalViews;
	}
	
}
