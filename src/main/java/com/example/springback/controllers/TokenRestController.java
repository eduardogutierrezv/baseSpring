package com.example.springback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.springback.vo.TokenReqVO;
import com.example.springback.dto.AuthTokenResponse;
import com.example.springback.jwtconfigure.seguridad.TokenProvider;
import com.example.springback.util.CodeResponse;
import com.example.springback.util.ResponseRestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth/login")
public class TokenRestController {

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "")
	public ResponseRestController<AuthTokenResponse> loginPrimary(@RequestBody TokenReqVO userLogin) { // TRAEMOS LOS

		// DATOS DEL
		ResponseRestController<AuthTokenResponse> resp = new ResponseRestController<AuthTokenResponse>();

		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			resp = tokenProvider.createToken(authentication);

			return resp;

		} catch (Exception e) {
			resp.setCode(CodeResponse.ERROR_SERVIDOR);
			resp.setMessage("ERROR MICROSERVICIO " + e);

			return resp;

		}

	}

}
