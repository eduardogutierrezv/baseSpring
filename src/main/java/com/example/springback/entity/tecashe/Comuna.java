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
@Table(name = "comunas")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdComuna")
    private Integer idComuna;

    @Column(name = "comuna")
    private String comuna;

    @Column(name = "IdProvincia")
    private Integer idProvincia;

}
