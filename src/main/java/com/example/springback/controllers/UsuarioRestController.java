package com.example.springback.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springback.entity.Usuario;
import com.example.springback.service.UsuarioService;
import com.example.springback.util.response.Error;
import com.example.springback.util.response.GenericResponse;
import com.example.springback.util.response.Success;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

	@Autowired
	private UsuarioService iusuarioservice;

	// CREAR USUARIO
	@PostMapping("/usuario")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<GenericResponse<Usuario>> usuarioCreado(@RequestBody Usuario usuario) {

		GenericResponse<Usuario> genResp = new GenericResponse<Usuario>();
		ArrayList<Error> errores = new ArrayList<Error>();

		try {

			genResp.setPayload(iusuarioservice.save(usuario));
			genResp.setSuccess(Success.OK);
			return new ResponseEntity<>(genResp, null, HttpStatus.OK);

		} catch (Exception e) {

			genResp.setSuccess(Success.NOK);
			Error er = new Error();

			er.setCodigo("400");
			er.setDescripcion("ERROR");
			errores.add(er);
			genResp.setErrores(errores);

			return new ResponseEntity<GenericResponse<Usuario>>(genResp, null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
