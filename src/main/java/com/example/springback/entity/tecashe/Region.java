package com.example.springback.entity.tecashe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "regiones")
@Data
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRegion")
    private Integer idRegion;

    @Column(name = "Region")
    private String region;

    @Column(name = "Abreviatura")
    private String abreviatura;

    @Column(name = "Capital")
    private String capital;

}
