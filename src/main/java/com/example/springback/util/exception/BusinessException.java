package com.example.springback.util.exception;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private ArrayList<BussinesErrorCode> bussinesErrorCodes;

	public BusinessException(ArrayList<BussinesErrorCode> bussinesErrorCodes) {
		this.bussinesErrorCodes = bussinesErrorCodes;
	}

}
