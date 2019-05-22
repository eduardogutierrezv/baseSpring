package com.example.springback.models.service;

import com.example.springback.models.entity.Token;

public interface ITokenServiceBd {

	public void guardarToken(Token token);
	
	public Token returnToken(String token);
}
