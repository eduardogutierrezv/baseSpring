package com.example.springback.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetRutVerificadorRespVO {

    private Integer estadoRespuesta;
    private Integer indVigencia;
    private String mensaje;
    private boolean isError;
}
