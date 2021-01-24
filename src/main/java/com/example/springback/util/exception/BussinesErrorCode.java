package com.example.springback.util.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BussinesErrorCode {

	private String codigo;

	public BussinesErrorCode(String codigo) {
		this.codigo = codigo;
	}

}
