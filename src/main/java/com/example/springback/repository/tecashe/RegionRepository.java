package com.example.springback.repository.tecashe;

import java.util.Optional;

import com.example.springback.entity.tecashe.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    Optional<Region> findByIdRegion(Integer idRegion);

}
