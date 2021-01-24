package com.example.springback.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.example.springback.entity.Usuario;
import com.example.springback.jwtconfigure.model.JwtUser;
import com.example.springback.jwtconfigure.seguridad.JwtGenerator;
import com.example.springback.repository.UsuarioRepo;
import com.example.springback.vo.TokenReqVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Autowired
	private UsuarioRepo userRepo;

	@Autowired
	private SeguridadPassService sPass;

	@Autowired
	private JwtGenerator jwtgenerator;

	public Map<String, Object> crearToken(TokenReqVO tokenBody) {

		String email = tokenBody.getEmail();// CAPTURAMOS LA VARIABLE QUE VIENE DEL POST Y LA TRANSFORMAMOS // EN STRING
		String password = tokenBody.getPassword(); // TRAEMOS EL PASSWORD DEL OBJETO POST
		boolean verificador = false;// INDICAMOS QUE EL VERIFICADOR DE CONTRASEÑA VIENE FALSE

		Usuario userBd = userRepo.findByEmail(email); // TRAEMOS EL PASS DE LA BASE DE DATOS
		verificador = sPass.desencriptarPass(password, userBd.getPassword()); // COMPARAMOS LA CONTRASEÑA QUE VIENE DEL
																				// POST CON LA
		// BASE DE DATOS
		// EN EL CASO DE SER TRUE ES CORRECTA
		String token = null; // VARIABLE TOKEN
		String code = null;
		String mensaje = null;
		// Map<String, Object> object = null; //CREAMOS EL OBJECT CON PROPIEDADES MAP
		Usuario object = new Usuario();
		Map<String, Object> responseToken = new LinkedHashMap<String, Object>(); // MAP INSTANCIADO SEGUN ENTREN LAS
																					// VARIABLES
		// VERIFICAMOS QUE LA CONTRASEÑA SEA IGUAL A LA QUE VIENE DEL USUARIO
		if (verificador == true) {
			JwtUser jwtUserToken = new JwtUser();
			jwtUserToken.setUsername(email);
			jwtUserToken.setPassword(userBd.getPassword());
			jwtUserToken.setRole("admin");

			code = "200";
			token = jwtgenerator.generate(jwtUserToken); // GENERAMOS TOKEN
			mensaje = "true"; // VERIFICAMOS QUE SE GUARDO

			try {

			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			token = "Login Incorrecto";
			mensaje = "Datos Incorrectos";
			code = "400";
			object = null;
		}

		responseToken.put("token", token);
		responseToken.put("mensaje", mensaje);
		responseToken.put("code", code);
		responseToken.put("object", object);

		return responseToken;
	}

	// public void guardarToken(Token token);

	// public Token returnToken(String token);
}
