package com.example.springback.jwtconfigure.seguridad;

import org.springframework.stereotype.Service;

import com.example.springback.jwtconfigure.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtGenerator {
	// GENERA TOKEN
	public String generate(JwtUser jwtUser) {

		Claims claims = Jwts.claims().setSubject(jwtUser.getUsername());
		claims.put("userId", jwtUser.getPassword());
		claims.put("role", jwtUser.getRole());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "Lo32!QW@$%/rtADcf24Adv$124")
				.compact();

	}

}
