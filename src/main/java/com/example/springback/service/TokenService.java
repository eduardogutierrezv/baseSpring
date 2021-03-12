package com.example.springback.service;

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

	public String crearToken(TokenReqVO tokenBody) {

		String email = tokenBody.getEmail();// CAPTURAMOS LA VARIABLE QUE VIENE DEL POST Y LA TRANSFORMAMOS // EN STRING
		String password = tokenBody.getPassword(); // TRAEMOS EL PASSWORD DEL OBJETO POST
		boolean verificador = false;// INDICAMOS QUE EL VERIFICADOR DE CONTRASEÑA VIENE FALSE

		Usuario userBd = userRepo.findByEmail(email); // TRAEMOS EL PASS DE LA BASE DE DATOS
		verificador = sPass.desencriptarPass(password, userBd.getPassword()); // COMPARAMOS LA CONTRASEÑA QUE VIENE DEL
																				// POST CON LA
		// BASE DE DATOS
		// EN EL CASO DE SER TRUE ES CORRECTA
		String token = null;

		if (verificador == true) {
			JwtUser jwtUserToken = new JwtUser();
			jwtUserToken.setUsername(email);
			jwtUserToken.setPassword(userBd.getPassword());
			jwtUserToken.setRole("nada");

			token = jwtgenerator.generate(jwtUserToken); // GENERAMOS TOKEN

		} else {
			token = null;
		}

		return token;
	}

}
