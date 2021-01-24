package com.example.springback.util.exception;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Deprecated
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	private ArrayList<ValidationErrorCode> validationErrorCodes;

	public ValidationException(ArrayList<ValidationErrorCode> validationErrorCodes) {
		this.validationErrorCodes = validationErrorCodes;
	}

}
