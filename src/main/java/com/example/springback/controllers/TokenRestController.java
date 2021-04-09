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

			System.err.println(userLogin);
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
			System.err.println(userLogin);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.err.println(authentication);

			AuthTokenResponse auth = tokenProvider.createToken(authentication);
			System.err.println(authentication);

			if (null == auth) {

				resp.setCode(400);
				resp.setMessage("Password Incorrecto");

			}

			resp.setCode(200);
			resp.setMessage("token generado");
			resp.setBody(auth);

			return resp;

		} catch (Exception e) {
			resp.setCode(500);
			resp.setMessage("ERROR MICROSERVICIO " + e);

			return resp;

		}

	}

}
