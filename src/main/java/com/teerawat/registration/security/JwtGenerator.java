package com.teerawat.registration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teerawat.registration.model.JwtUser;
import com.teerawat.registration.utils.DateUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	
	
	@Autowired
	private DateUtil dateUtil;

    public String generate(JwtUser user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("authDate", dateUtil.getCurrentDateInNormalFormat());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, JwtValidator.SECRET)
                .compact();
    }

}
