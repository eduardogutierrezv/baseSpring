package com.example.springback.service;

import com.example.springback.entity.tecashe.Usuario;
import com.example.springback.repository.tecashe.UsuarioRepository;
import com.example.springback.util.ResponseRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository useRepo;

	@Autowired
	private SeguridadPassService sPass;

	public ResponseRestController<Usuario> saveUser(Usuario user) {

		Usuario usuarioSave = new Usuario();
		ResponseRestController<Usuario> resp = new ResponseRestController<Usuario>();

		try {

			usuarioSave.setRut(user.getRut());
			usuarioSave.setNombre(user.getNombre());
			usuarioSave.setApellido(user.getApellido());
			usuarioSave.setTelefono(user.getTelefono());
			usuarioSave.setIdRegion(1);
			usuarioSave.setIdComuna(1);
			usuarioSave.setIdProvincia(1);
			usuarioSave.setEmail(user.getEmail());
			usuarioSave.setPassword(this.PassEncypter(user.getPassword()));
			usuarioSave.setIdEstadoUsuario(1);

			if (useRepo.save(usuarioSave) == null) {
				resp.setCode(400);
				resp.setMessage("No se pudo guardar el usuario");

			} else {

				resp.setCode(200);
				resp.setMessage("Usuario guardado con exito");
				resp.setBody(usuarioSave);
			}

		} catch (Exception e) {

			resp.setCode(500);
			resp.setMessage("Servicio no disponible " + e);
		}

		return resp;

	}

	private String PassEncypter(String passSinencrypter) {
		return this.sPass.encriptarPass(passSinencrypter);
	}

}
