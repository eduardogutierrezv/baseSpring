package com.example.springback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springback.entity.Usuario;
import com.example.springback.repository.UsuarioRepo;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private SeguridadPassService sPass;

	public Usuario save(Usuario user) {

		Usuario usuarioSave = new Usuario();

		usuarioSave.setNombre(user.getNombre());
		usuarioSave.setApellido(user.getApellido());
		usuarioSave.setEmail(user.getEmail());
		usuarioSave.setEdad(user.getEdad());
		usuarioSave.setFecha(user.getFecha());
		usuarioSave.setPassword(sPass.encriptarPass(user.getPassword()));

		return usuarioRepo.save(usuarioSave);

	}

}
