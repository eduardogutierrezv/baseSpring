package com.example.springback.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.springback.util.response.GenericResponse;
import com.example.springback.util.response.Success;
import com.example.springback.vo.TokenReqVO;
import com.example.springback.jwtconfigure.seguridad.TokenProvider;
import com.example.springback.service.TokenService;
import com.example.springback.util.response.Error;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth/login")
public class TokenRestController {

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "")
	public ResponseEntity<GenericResponse<String>> loginPrimary(@RequestBody TokenReqVO userLogin) { // TRAEMOS LOS
																										// DATOS DEL

		GenericResponse<String> genResp = new GenericResponse<String>();
		ArrayList<Error> errores = new ArrayList<Error>();

		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = tokenProvider.createToken(authentication);

			if (null == token) {

				genResp.setSuccess(Success.NOK);
				Error er = new Error();
				er.setCodigo("400-1");
				er.setDescripcion("Contraseña incorrecta");
				errores.add(er);
				genResp.setErrores(errores);

				return new ResponseEntity<GenericResponse<String>>(genResp, null, HttpStatus.NOT_ACCEPTABLE);

			}

			genResp.setPayload(token);
			genResp.setSuccess(Success.OK);
			return new ResponseEntity<>(genResp, null, HttpStatus.OK);

		} catch (Exception e) {

			genResp.setSuccess(Success.NOK);
			Error er = new Error();

			er.setCodigo("400");
			er.setDescripcion("ERROR");
			errores.add(er);
			genResp.setErrores(errores);

			return new ResponseEntity<GenericResponse<String>>(genResp, null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}
