package com.example.springback.util.response;

import java.util.ArrayList;

public class GenericResponse<T> {

	Success success = Success.OK;

	T payload = null;
	ArrayList<Error> errores = null;

	/* Get y Set */
	public ArrayList<Error> getErrores() {
		return errores;
	}

	public void setErrores(ArrayList<Error> errores) {
		this.errores = errores;
	}

	public Success getSuccess() {
		return success;
	}

	public void setSuccess(Success success) {
		this.success = success;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

}
