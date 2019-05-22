package com.example.springback.models.controllers;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.tools.ISupportsMessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springback.clasesolas.MensajeJson;
import com.example.springback.models.entity.SeguridadPass;
import com.example.springback.models.entity.Usuario;
import com.example.springback.models.service.IUsuarioService;


@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService iusuarioservice;
	SeguridadPass sPass = new SeguridadPass();
	MensajeJson msnJson = new MensajeJson();

	//CREAR USUARIO
	@PostMapping("/usuario")
	@ResponseStatus(code = HttpStatus.OK)
	public Map<String, String> usuarioCreado(@RequestBody Usuario usuario) {

		Usuario user = new Usuario();
		user.setNombre(usuario.getNombre());
		user.setApellido(usuario.getApellido());
		user.setEmail(usuario.getEmail());
		user.setEdad(usuario.getEdad());
		user.setFecha(usuario.getFecha());
		user.setPassword(sPass.encriptarPass(usuario.getPassword()));
		iusuarioservice.save(user);

		return msnJson.mensajeString("Usuario Creado", "200", "success");
	}

	//MOSTRAR TODOS LOS USARIOS
	@GetMapping("/usuario")
	public List<Usuario> todos_usuarios() {
		return iusuarioservice.findAll();
	}

	// MOSTRAR USUARIO POR ID
	@GetMapping("/usuario/{email}")
	public Map<String, Object> usuarioId(@PathVariable String email) {
		return iusuarioservice.buscarUsuarioEmail(email);
	}

	//ELIMINAR USUARIO
	@DeleteMapping("/usuario/{id}") // CAPTURAMOS LA RUTA DEL USUARIO
	@ResponseStatus(code = HttpStatus.OK) // VERIFIQUEMOS QUE ESTA OK
	public String eliminarUsuario(@PathVariable int id) {
		return iusuarioservice.eliminarUsuario(id);
	}
	
	// ACTUALIZAR USUARIO 
	@PutMapping("/usuario/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Map<String, String> usuarioActualizado(@RequestBody Usuario userPost, @PathVariable int id) {
		Usuario userGuardar = iusuarioservice.findById(id);
		if (!userGuardar.equals("") && !userGuardar.equals(null)) {

			userGuardar.setNombre(userPost.getNombre());
			userGuardar.setEdad(userPost.getEdad());

			return msnJson.mensajeString("Usuario Actualizado", "200", "success");
		} else {

			return msnJson.mensajeString("Error al Actualizar", "404", "error");
		}

	}

	// CAMBIAR LA CONTRASEÑA
	@PutMapping("/usuariocambiarpass/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Map<String, String> cambiarContraseña(@RequestBody Map<String, String> user, @PathVariable int id) {

		Usuario userDb = iusuarioservice.findById(id);
		boolean validador = false;
		String mensaje = null;
		String code = null;
		String status = null;
		try {
			if (userDb.toString() != "" || userDb != null) {
				validador = sPass.desencriptarPass(user.get("password"), userDb.getPassword());

				if (validador) {
					if (user.get("password_nuevo") != "") {
						String passNuevo = sPass.encriptarPass(user.get("password_nuevo"));
						userDb.setPassword(passNuevo);
						iusuarioservice.save(userDb);

						mensaje = "Contraseña Cambiada";
						code = "200";
						status = "success";
					} else {
						mensaje = "Contraseña Nueva Vacia";
						code = "400";
						status = "error";
					}
				} else {
					mensaje = "Error Contraseña Incorrecta";
					code = "400";
					status = "error";
				}
			}

		} catch (Exception e) {
			mensaje = "Error al guardar " + e;
			code = "404";
			status = "error";
		}

		return msnJson.mensajeString(mensaje, code, status);
	}
}
