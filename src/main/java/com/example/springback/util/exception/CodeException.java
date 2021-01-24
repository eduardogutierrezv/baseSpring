package com.example.springback.util.exception;

public enum CodeException {

    ERROR_VALIDACION(400), ERROR_NEGOCIO(422);

    private Integer data;

    CodeException(Integer data) {
        this.data = data;
    }

    public Integer data() {
        return data;
    }
}
