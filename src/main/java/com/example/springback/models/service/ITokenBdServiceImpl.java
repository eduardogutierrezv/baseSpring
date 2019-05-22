package com.example.springback.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springback.models.dao.ITokenDao;
import com.example.springback.models.entity.Token;

@Service
public class ITokenBdServiceImpl implements ITokenServiceBd{

	@Autowired
	private ITokenDao itokendao;

	@Override
	public void guardarToken(Token token) {
		itokendao.save(token);
	}

	@Override
	public Token returnToken(String token) {
		return null;
	}
	
}
