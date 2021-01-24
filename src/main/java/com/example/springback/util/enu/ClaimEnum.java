package com.example.springback.util.enu;

public enum ClaimEnum {

    CODIGO_ORGANISMO("codigoOrganismo"), CODIGO_USUARIO("codigoUsuario");

    private String value;

    ClaimEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
