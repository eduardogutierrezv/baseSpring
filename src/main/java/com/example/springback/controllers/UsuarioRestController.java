package com.example.springback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springback.entity.Usuario;
import com.example.springback.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

	@Autowired
	private UsuarioService iusuarioservice;

	// CREAR USUARIO
	@PostMapping
	public Usuario usuarioCreado(@RequestBody Usuario usuario) {

		try {

			return iusuarioservice.save(usuario);

		} catch (Exception e) {

			return iusuarioservice.save(usuario);

		}

	}

}
