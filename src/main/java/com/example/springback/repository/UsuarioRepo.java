package com.example.springback.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.springback.entity.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT email, password FROM usuarios WHERE usuarios.email = ?1 AND usuarios.password = ?2", nativeQuery = true)
	Map<String, Object> loginPrueba(String email, String password);

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findById(Integer id);

	/*
	 * @Query(value = "SELECT password FROM usuarios WHERE usuarios.email = ?1",
	 * nativeQuery = true) String soloPass(String email);
	 * 
	 * @Query(value =
	 * "SELECT nombre, apellido, edad, email FROM usuarios WHERE usuarios.email = ?1"
	 * , nativeQuery = true) Map<String, Object> buscarUsuarioEmail(String email);
	 * 
	 * @Query(value = "SELECT * FROM usuarios WHERE usuarios.email = ?1",
	 * nativeQuery = true) Usuario buscarUsuarioEmailAndReturnId(String email);
	 */

}
