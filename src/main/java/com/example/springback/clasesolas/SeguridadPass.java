package com.example.springback.clasesolas;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class SeguridadPass {

	public SeguridadPass() { // CONSTRUCTOR

	}

	public String encriptarPass(String password) { // ENCRIPTADOR DE CONTRASEÑA
		
		String clave = ""; // DEFINIMOS LA VARIABLE
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // ENCRIPTAMOS
		for (int i = 0; i < 12; i++) { // CATIDAD DE ROUND QUE SE LE INYECTA LA SALT
			String hashedPassword = passwordEncoder.encode(password); // CREAMOS LA VARIABLE HASHED Y ENCONDOTAMOS EL PASSWORD QUE VIENE DEL USUARIO
			clave = hashedPassword; // PASAMOS LA CLAVE HASHEDPASSWORD
		}

		return clave; // RETORNAMOS CLAVE
	}

	public boolean desencriptarPass(String passwordSin, String passEncriptado) { // FUNCTION QUE VALIDAMOS PASSWORD
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // CREAMOS LA VARIABLE BCRYPT
		return passwordEncoder.matches(passwordSin, passEncriptado); // DEVOLVEMOS EL POUND SI ES VERDADERO
		}
}
