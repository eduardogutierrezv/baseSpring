package com.example.springback.models.service;


import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.springback.models.dao.IUsuariosDao;
import com.example.springback.models.entity.Usuario;

@Service
public class UsuarioServiceImplmen implements IUsuarioService{

	@Autowired
	private IUsuariosDao iusuariosDao;

	@Override
	public Usuario save(Usuario user) {
		return iusuariosDao.save(user);
	}
	
	public Usuario crear_usuario(Usuario usuario) {	
		return iusuariosDao.save(usuario);
	}

	@Override
	public Map<String, Object> login_usuario(String email, String password) {
		return iusuariosDao.loginPrueba(email, password);
	}

	@Override
	public String verificador_pass(String email) {
		return iusuariosDao.soloPass(email);
	}

	@Override
	public List<Usuario> findAll() {
		return iusuariosDao.findAll();
	}

	@Override
	public Map<String, Object> buscarUsuarioEmail(String email) {
		return iusuariosDao.buscarUsuarioEmail(email);
	}

	@Override
	public String eliminarUsuario(int id) {
		String mensaje;
		try {
			iusuariosDao.deleteById(id);
			mensaje = "Usuario Eliminado";
			return mensaje;
		} catch ( Exception ex) {
			mensaje ="404";
			return mensaje;
		}
	}
	
	@Override
	public Usuario findById(int id) {
		return iusuariosDao.findById(id).orElse(null);
	}
	
	@Override
	public ResponseEntity<Object> actualizarDatos(Usuario user) {
		try {
			iusuariosDao.save(user);
			return ResponseEntity.accepted().build();
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
		
	}

	@Override
	public Usuario buscarUsuarioAndReturnId(String email) {
		return iusuariosDao.buscarUsuarioEmailAndReturnId(email);
	}


}
