package com.example.springback.jwtconfigure.seguridad;

import org.springframework.stereotype.Component;

import com.example.springback.jwtconfigure.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	private String secretKey = "Lo32!QW@$%/rtADcf24Adv$124";
	
	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		try {
		Claims body = Jwts.parser()
					   .setSigningKey(secretKey)
					   .parseClaimsJws(token)
					   .getBody();
		
		jwtUser = new JwtUser();
		jwtUser.setUsername(body.getSubject());
		jwtUser.setPassword((String)body.get("password"));
		jwtUser.setRole((String)body.get("role"));
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return jwtUser;
	}

}
