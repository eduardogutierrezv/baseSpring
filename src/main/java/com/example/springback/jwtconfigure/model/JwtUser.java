package com.example.springback.jwtconfigure.model;

import lombok.Data;

@Data
public class JwtUser {

	private String username;
	private String password;
	private String role;

}
