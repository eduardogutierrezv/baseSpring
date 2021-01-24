package com.example.springback.util.exception;

import java.util.ArrayList;

import com.example.springback.util.response.GenericResponse;
import com.example.springback.util.response.Success;
import com.example.springback.util.utils.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import com.example.springback.util.response.Error;

@Slf4j
public class HandlerException<T> {

	@SuppressWarnings({ "deprecation" })
	public ResponseEntity<GenericResponse<?>> process(Exception ex) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/problem+json;charset=UTF-8");
		GenericResponse<?> genResp = new GenericResponse<>();
		genResp.setSuccess(Success.NOK);
		HttpStatus status = null;

		StringBuffer stackTrace = new StringBuffer();
		if (ex.getStackTrace() != null) {
			StackTraceElement[] stackTraceArray = ex.getStackTrace();
			for (int i = 0; i < stackTraceArray.length; i++) {
				stackTrace.append(stackTraceArray[i] + "\n");
			}
		}

		if (ex instanceof ValidationException) {
			ValidationException vex = (ValidationException) ex;
			ArrayList<Error> errores = new ArrayList<Error>();
			Error error;
			for (ValidationErrorCode vErrorCode : vex.getValidationErrorCodes()) {
				error = new Error();
				error.setCodigo(vErrorCode.getCodigo());
				error.setDescripcion(getErrorMessage(vErrorCode.getCodigo(), vErrorCode.getCampo()));
				errores.add(error);
				log.warn("Codigo: " + error.getCodigo());
				log.warn("Descripcion: " + error.getDescripcion());
			}
			genResp.setErrores(errores);
			status = HttpStatus.BAD_REQUEST;

		} else if (ex instanceof BusinessException) {
			BusinessException bex = (BusinessException) ex;
			ArrayList<Error> errores = new ArrayList<Error>();
			Error error;
			for (BussinesErrorCode bErrorCode : bex.getBussinesErrorCodes()) {
				error = new Error();
				error.setCodigo(bErrorCode.getCodigo());
				error.setDescripcion(getErrorMessage(bErrorCode.getCodigo(), null));
				errores.add(error);
				log.warn("Codigo: " + error.getCodigo());
				log.warn("Descripcion: " + error.getDescripcion());
			}
			genResp.setErrores(errores);
			status = HttpStatus.UNPROCESSABLE_ENTITY;

		} else {
			String cause = null, message = null;
			if (ex.getCause() != null) {
				cause = ex.getCause().toString();
			}
			if (ex.getMessage() != null) {
				message = ex.getMessage();
			}

			log.error("Cause: " + cause);
			log.error("Message: " + message);
			log.error("StackTrace: \n" + stackTrace.toString());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<GenericResponse<?>>(genResp, headers, status);
	}

	private String getErrorMessage(String errorCode, String campo) {
		String retorno = null;
		if (campo != null) {
			retorno = Utils.getValue(errorCode).replace("%c", campo);
		} else {
			retorno = Utils.getValue(errorCode.toString());
		}

		return retorno;
	}

	@Deprecated
	public ResponseEntity<GenericResponse<T>> process(Exception ex, GenericResponse<T> genResp, HttpHeaders headers,
			String traceCode) {

		headers.set("Content-Type", "application/problem+json;charset=UTF-8");
		genResp.setSuccess(Success.NOK);
		HttpStatus status = null;

		StringBuffer stackTrace = new StringBuffer();
		if (ex.getStackTrace() != null) {
			StackTraceElement[] stackTraceArray = ex.getStackTrace();
			for (int i = 0; i < stackTraceArray.length; i++) {
				stackTrace.append(stackTraceArray[i] + "\n");
			}
		}

		if (ex instanceof ValidationException) {
			ValidationException vex = (ValidationException) ex;
			ArrayList<Error> errores = new ArrayList<Error>();
			Error error;
			for (ValidationErrorCode vErrorCode : vex.getValidationErrorCodes()) {
				error = new Error();
				error.setCodigo(vErrorCode.getCodigo());
				error.setDescripcion(getErrorMessage(vErrorCode.getCodigo(), vErrorCode.getCampo(), traceCode));
				errores.add(error);
				log.warn("trace: " + traceCode + " Codigo: " + error.getCodigo());
				log.warn("trace: " + traceCode + " Descripcion: " + error.getDescripcion());
			}
			genResp.setErrores(errores);
			status = HttpStatus.BAD_REQUEST;

		} else if (ex instanceof BusinessException) {
			BusinessException bex = (BusinessException) ex;
			ArrayList<Error> errores = new ArrayList<Error>();
			Error error;
			for (BussinesErrorCode bErrorCode : bex.getBussinesErrorCodes()) {
				error = new Error();
				error.setCodigo(bErrorCode.getCodigo());
				error.setDescripcion(getErrorMessage(bErrorCode.getCodigo(), null, traceCode));
				errores.add(error);
				log.warn("trace: " + traceCode + " Codigo: " + error.getCodigo());
				log.warn("trace: " + traceCode + " Descripcion: " + error.getDescripcion());
			}
			genResp.setErrores(errores);
			status = HttpStatus.UNPROCESSABLE_ENTITY;

		} else {
			String cause = null, message = null;
			if (ex.getCause() != null) {
				cause = ex.getCause().toString();
			}
			if (ex.getMessage() != null) {
				message = ex.getMessage();
			}

			log.error("trace: " + traceCode + " Cause: " + cause);
			log.error("trace: " + traceCode + " Message: " + message);
			log.error("trace: " + traceCode + " StackTrace: \n" + stackTrace.toString());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<GenericResponse<T>>(genResp, headers, status);
	}

	@Deprecated
	private String getErrorMessage(String errorCode, String campo, String traceCode) {
		String retorno = null;
		if (campo != null) {
			retorno = Utils.getValue(errorCode, traceCode).replace("%c", campo);
		} else {
			retorno = Utils.getValue(errorCode.toString(), traceCode);
		}

		return retorno;
	}

}
