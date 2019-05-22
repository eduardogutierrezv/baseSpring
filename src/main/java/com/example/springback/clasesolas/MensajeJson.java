package com.example.springback.clasesolas;

import java.util.LinkedHashMap;
import java.util.Map;

public class MensajeJson {


	
	public MensajeJson() {
		
	}
	
	public Map<String, String> mensajeString(String mensaje, String code, String status){
		
		Map<String, String> mensajeCompleto = new LinkedHashMap<String, String>();
		mensajeCompleto.put("mensaje", mensaje);
		mensajeCompleto.put("code", code);
		mensajeCompleto.put("status", status );
		
		return mensajeCompleto;
	}
	
	public Map<String, Object> mensajeObject(Object object){
		
		Map<String, Object> mensajeObject = new LinkedHashMap<String, Object>();
		
		mensajeObject.put("objeto", object);
		
		
		return mensajeObject;
	}
}
