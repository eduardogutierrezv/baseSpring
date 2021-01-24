package com.example.springback.util.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Deprecated
public class ValidationErrorCode {

	private String codigo;
	private String campo;

	public ValidationErrorCode(String codigo, String campo) {
		this.codigo = codigo;
		this.campo = campo;
	}

}
