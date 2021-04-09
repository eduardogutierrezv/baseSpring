package com.example.springback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springback.entity.tecashe.Usuario;
import com.example.springback.service.UsuarioService;
import com.example.springback.util.ResponseRestController;

@RestController
@RequestMapping("/auth/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

	@Autowired
	private UsuarioService iusuarioservice;

	// CREAR USUARIO
	@PostMapping
	public ResponseRestController<Usuario> usuarioCreado(@RequestHeader String password) {
		Usuario usuarioSave = new Usuario();
		usuarioSave.setRut("18-111751");
		usuarioSave.setNombre("Eduardo");
		usuarioSave.setApellido("gutierrez valdes");
		usuarioSave.setTelefono("949138183");
		usuarioSave.setIdRegion(1);
		usuarioSave.setIdComuna(1);
		usuarioSave.setIdProvincia(1);
		usuarioSave.setEmail("edu@gmail.com");
		usuarioSave.setPassword(password);
		usuarioSave.setIdEstadoUsuario(1);

		ResponseRestController<Usuario> resp = new ResponseRestController<Usuario>();

		try {

			resp = iusuarioservice.saveUser(usuarioSave);

		} catch (Exception e) {
			System.err.println("Mensaje Error");
		}

		return resp;

	}

}
