package com.teerawat.registration.services;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teerawat.registration.db.entities.LoginEntity;
import com.teerawat.registration.db.entities.UserEntity;
import com.teerawat.registration.db.repositories.LoginRepository;
import com.teerawat.registration.db.repositories.UserRepository;
import com.teerawat.registration.model.JwtUser;
import com.teerawat.registration.model.RegisterReqMsg;
import com.teerawat.registration.model.RegisterResMsg;
import com.teerawat.registration.model.RegisterResMsg.ResponseBody;
import com.teerawat.registration.security.JwtGenerator;
import com.teerawat.registration.utils.RegistrationBusinessUtil;
import com.teerawat.registration.utils.TripleDESUtil;

@Service
public class RegisterService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private TripleDESUtil tripleDESUtil;
	
	@Autowired
	private RegistrationBusinessUtil bussinessUtil;
	
	@Autowired
	private JwtGenerator jwtGenerator;

	public RegisterResMsg register(RegisterReqMsg req) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setName(req.getName());
		userEntity.setAddress(req.getAddress());
		userEntity.setPhone(req.getPhone());
		userEntity.setReference(bussinessUtil.generateReferenceCode(userEntity.getPhone()));
		userEntity.setSalary(Integer.parseInt(req.getSalary()));
		if(userEntity.getSalary() < 15000) {
			return ResponseBody.CannotClassify.getRegisterResMsg();
		}

		LoginEntity loginEntity = new LoginEntity();
		loginEntity.setUsername(req.getUsername());
		try {
			loginEntity.setPassword(DatatypeConverter.printBase64Binary((tripleDESUtil.encrypt(req.getPassword()))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		userEntity.setLoginid(loginEntity);
		LoginEntity exitLogin = loginRepository.findByUsername(loginEntity.getUsername());
		if (null == exitLogin) {
			userRepository.save(userEntity);
			RegisterResMsg res = ResponseBody.Success.getRegisterResMsg();
			res.setAuthToken(jwtGenerator.generate(new JwtUser(loginEntity)));
			return res;
		} else {
			return ResponseBody.ExistUsername.getRegisterResMsg();
		}

	}
}
