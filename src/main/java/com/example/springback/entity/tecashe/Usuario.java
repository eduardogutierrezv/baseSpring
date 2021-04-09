package com.example.springback.entity.tecashe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer idUsuario;

    @Column(name = "Rut")
    private String rut;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "IdRegion")
    private Integer idRegion;

    @Column(name = "IdComuna")
    private Integer idComuna;

    @Column(name = "IdProvincia")
    private Integer idProvincia;

    @Column(name = "Email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "IdEstadoUsuario")
    private Integer idEstadoUsuario;

}
