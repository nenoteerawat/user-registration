package com.teerawat.registration.security;

import org.springframework.stereotype.Component;

import com.teerawat.registration.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	public static final String SECRET = "secret";

    public JwtUser validate(String token) {

    	JwtUser user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            user = new JwtUser();
            
            user.setUsername(body.getSubject());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
