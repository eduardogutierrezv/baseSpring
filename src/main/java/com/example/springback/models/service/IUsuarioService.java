package com.example.springback.models.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.springback.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario save(Usuario user);
	public Usuario crear_usuario(Usuario usuario); //GUARDAR USUARIO
	public String verificador_pass(String email); //TRAER E VERIFICADOR DEL PASS
	public Map<String, Object> login_usuario(String email, String password); //BUSCAR USUARIO POR PASSWORD
	public List<Usuario> findAll(); //OBTENER USUARIO POR ID
	public Map<String, Object> buscarUsuarioEmail(String email); //BUSCAR USUARIO POR EMAIL
	public String eliminarUsuario(int id);
	public ResponseEntity<Object> actualizarDatos(Usuario user);
	public Usuario findById(int id);
	public Usuario buscarUsuarioAndReturnId(String email); //BUSCAR USUARIO POR ID
}
