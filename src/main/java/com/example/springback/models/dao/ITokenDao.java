package com.example.springback.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springback.models.entity.Token;

public interface ITokenDao extends JpaRepository<Token, Integer>{
	
	
}
