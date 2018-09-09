package com.teerawat.registration.services;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teerawat.registration.db.entities.LoginEntity;
import com.teerawat.registration.db.repositories.LoginRepository;
import com.teerawat.registration.model.JwtUser;
import com.teerawat.registration.model.RegisterResMsg;
import com.teerawat.registration.model.RegisterResMsg.ResponseBody;
import com.teerawat.registration.security.JwtGenerator;
import com.teerawat.registration.utils.TripleDESUtil;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private TripleDESUtil tripleDESUtil;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	public RegisterResMsg login(JwtUser user) {
		LoginEntity loginEntity = loginRepository.findByUsername(user.getUsername());
		String password = null;
		try {
			password = tripleDESUtil.decrypt(DatatypeConverter.parseBase64Binary(loginEntity.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != password && password.equals(user.getPassword())) {
			RegisterResMsg res = ResponseBody.Success.getRegisterResMsg();
			res.setAuthToken(jwtGenerator.generate(user));
			return res;
		} else {
			return ResponseBody.AuthenticationFail.getRegisterResMsg();
		}
	}
}
