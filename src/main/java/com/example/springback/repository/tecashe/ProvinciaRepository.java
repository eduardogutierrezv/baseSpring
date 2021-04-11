package com.example.springback.repository.tecashe;

import java.util.List;
import java.util.Optional;

import com.example.springback.entity.tecashe.Provincia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

    Optional<List<Provincia>> findByIdRegion(Integer idRegion);

}
