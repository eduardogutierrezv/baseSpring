package com.example.springback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springback.entity.tecashe.Usuario;
import com.example.springback.service.UsuarioService;
import com.example.springback.util.ResponseRestController;
import com.example.springback.vo.RegisterUsuarioReqVO;

@RestController
@RequestMapping("/public/v1")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

	@Autowired
	private UsuarioService iusuarioservice;

	// CREAR USUARIO
	@PostMapping("/register-usuario")
	public ResponseRestController<Usuario> usuarioCreado(@RequestBody RegisterUsuarioReqVO regUser) {

		ResponseRestController<Usuario> resp = new ResponseRestController<Usuario>();

		try {
			if (iusuarioservice.ValidarDigitoVerificador(regUser.getRut(), regUser.getNumeroSerie())) {
				resp = iusuarioservice.saveUser(regUser);
			} else {
				// no corresponde el numero de serie
			}

		} catch (Exception e) {
			System.err.println("Mensaje Error");
		}

		return resp;
	}

}
