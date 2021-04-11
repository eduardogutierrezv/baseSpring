package com.example.springback.repository.tecashe;

import java.util.List;
import java.util.Optional;

import com.example.springback.entity.tecashe.Comuna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

    Optional<List<Comuna>> findByIdProvincia(Integer idProvincia);

}
