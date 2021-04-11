package com.example.springback.service;

import com.example.springback.entity.tecashe.Usuario;
import com.example.springback.repository.tecashe.UsuarioRepository;
import com.example.springback.util.CodeResponse;
import com.example.springback.util.ResponseRestController;
import com.example.springback.vo.RegisterUsuarioReqVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private static Integer ESTADO_USUARIO_ACTIVADO = 1;

	private static Integer ESTADO_DV_CORRECTO = 1;

	@Autowired
	private ObtenerDigitoVerificadorService dvService;

	@Autowired
	private UsuarioRepository useRepo;

	@Autowired
	private SeguridadPassService seguridadPassword;

	public ResponseRestController<Usuario> saveUser(RegisterUsuarioReqVO regUser) {

		Usuario usuarioSave = new Usuario();
		ResponseRestController<Usuario> resp = new ResponseRestController<Usuario>();

		try {

			usuarioSave.setRut(regUser.getRut());
			usuarioSave.setNombre(regUser.getNombre());
			usuarioSave.setApellido(regUser.getApellido());
			usuarioSave.setTelefono(regUser.getTelefono());
			usuarioSave.setIdRegion(regUser.getIdRegion());
			usuarioSave.setIdComuna(regUser.getIdComuna());
			usuarioSave.setIdProvincia(regUser.getIdProvincia());
			usuarioSave.setEmail(regUser.getEmail());
			usuarioSave.setPassword(this.PassEncypter(regUser.getPassword()));
			usuarioSave.setIdEstadoUsuario(ESTADO_USUARIO_ACTIVADO);

			if (useRepo.save(usuarioSave) == null) {
				resp.setCode(CodeResponse.ERROR);
				resp.setMessage("No se pudo guardar el usuario");

			} else {

				resp.setCode(CodeResponse.SUCCESS);
				resp.setMessage("Usuario guardado con exito");
				resp.setBody(usuarioSave);
			}

		} catch (Exception e) {

			resp.setCode(CodeResponse.ERROR_SERVIDOR);
			resp.setMessage("Servicio no disponible " + e);
		}

		return resp;

	}

	public boolean ValidarDigitoVerificador(String rut, String numeroSerie) {

		if (this.dvService.getDigitoVerificador(rut, numeroSerie).getBody().getIndVigencia() == ESTADO_DV_CORRECTO) {
			return true;
		}
		return false;
	}

	private String PassEncypter(String passSinencrypter) {
		return this.seguridadPassword.encriptarPass(passSinencrypter);
	}

}
