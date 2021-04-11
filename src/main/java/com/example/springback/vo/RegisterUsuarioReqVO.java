package com.example.springback.vo;

import lombok.Data;

@Data
public class RegisterUsuarioReqVO {

    private String rut;
    private String numeroSerie;
    private String nombre;
    private String apellido;
    private String telefono;
    private Integer idRegion;
    private Integer idComuna;
    private Integer idProvincia;
    private String email;
    private String password;
    private Integer idEstadoUsuario = 1;
}
