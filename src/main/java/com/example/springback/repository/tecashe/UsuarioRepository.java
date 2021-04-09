package com.example.springback.repository.tecashe;

import java.util.Map;
import java.util.Optional;

import com.example.springback.entity.tecashe.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT email, password FROM usuarios WHERE usuarios.email = ?1 AND usuarios.password = ?2", nativeQuery = true)
    Map<String, Object> loginPrueba(String email, String password);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByIdUsuario(Integer id);

}
