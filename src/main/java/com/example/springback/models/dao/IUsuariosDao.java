package com.example.springback.models.dao;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springback.models.entity.Usuario;

public interface IUsuariosDao extends JpaRepository<Usuario, Integer>{
	
	@Query(value = "SELECT email, password FROM usuarios WHERE usuarios.email = ?1 AND usuarios.password = ?2", nativeQuery = true)
	Map<String, Object> loginPrueba(String email, String password);
	
	@Query(value = "SELECT password FROM usuarios WHERE usuarios.email = ?1", nativeQuery = true)
	 String soloPass(String email);
	
	@Query(value = "SELECT nombre, apellido, edad, email FROM usuarios WHERE usuarios.email = ?1", nativeQuery = true)
	Map<String, Object> buscarUsuarioEmail(String email);
	
	@Query(value = "SELECT * FROM usuarios WHERE usuarios.email = ?1", nativeQuery = true)
	Usuario buscarUsuarioEmailAndReturnId(String email);
	
	
}
