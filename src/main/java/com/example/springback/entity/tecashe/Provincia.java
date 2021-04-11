package com.example.springback.entity.tecashe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProvincia")
    private Integer idProvincia;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "IdRegion")
    private Integer idRegion;

}
