package com.example.springback.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springback.service.TokenService;

import com.example.springback.vo.TokenReqVO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TokenRestController {

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/login")
	public Map<String, Object> login_primario(@RequestBody TokenReqVO userLogin) { // TRAEMOS LOS DATOS DEL

		// POST COMO OBJETO
		return tokenService.crearToken(userLogin);
	}
}
