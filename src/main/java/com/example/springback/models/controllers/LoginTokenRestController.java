package com.example.springback.models.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springback.jwtconfigure.model.JwtUser;
import com.example.springback.jwtconfigure.seguridad.JwtGenerator;
import com.example.springback.models.entity.SeguridadPass;
import com.example.springback.models.entity.Token;
import com.example.springback.models.entity.Usuario;
import com.example.springback.models.service.ITokenServiceBd;
import com.example.springback.models.service.IUsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class TokenRestController {
	
	private JwtGenerator jwtgenerator;
	
	@Autowired 
	private IUsuarioService iusuarioservice; //INSTACIO EL IUSUARIOSERVICE QUE ES LA INTERFACE
	SeguridadPass sPass = new SeguridadPass(); //ACA INSTANCIA LA CLASE DEL PASSWORD
	Token tokenObjetto = new Token();
	
	@Autowired
	private ITokenServiceBd itokenservicebd;
	
	
	 public TokenRestController(JwtGenerator jwtgenerator) {
		this.jwtgenerator = jwtgenerator;
	}
	 	@PostMapping
		public Map<String, Object> login_primario(@RequestBody Map<String, Object> userLogin) { //TRAEMOS LOS DATOS DEL POST COMO OBJETO
			
			String email = userLogin.get("email").toString();//CAPTURAMOS LA VARIABLE QUE VIENE DEL POST Y LA TRANSFORMAMOS EN STRING
			String password =  userLogin.get("password").toString(); //TRAEMOS EL PASSWORD DEL OBJETO POST
			boolean verificador = false;//INDICAMOS QUE EL VERIFICADOR DE CONTRASEÑA VIENE FALSE	
			String passBd = iusuarioservice.verificador_pass(email); //TRAEMOS EL PASS DE LA BASE DE DATOS
			verificador = sPass.desencriptarPass(password, passBd); //COMPARAMOS LA CONTRASEÑA QUE VIENE DEL POST CON LA BASE DE DATOS
															//EN EL CASO DE SER TRUE ES CORRECTA
			String token = null; //VARIABLE TOKEN
			String code = null;
			String mensaje = null;
			//Map<String, Object> object = null; //CREAMOS EL OBJECT CON PROPIEDADES MAP
			Usuario object = new Usuario();
			Map<String, Object>responseToken = new LinkedHashMap<String, Object>(); //MAP INSTANCIADO SEGUN ENTREN LAS VARIABLES
		//VERIFICAMOS QUE LA CONTRASEÑA SEA IGUAL A LA QUE VIENE DEL USUARIO	
		if(verificador == true) {
			JwtUser jwtUserToken = new JwtUser();
			jwtUserToken.setUsername(email);
			jwtUserToken.setPassword(passBd);
			jwtUserToken.setRole("admin");
			
			code = "200";
			token = jwtgenerator.generate(jwtUserToken); //GENERAMOS TOKEN
			mensaje = "true"; //VERIFICAMOS QUE SE GUARDO
			object = iusuarioservice.buscarUsuarioAndReturnId(email);
			
		
				try {
					
					tokenObjetto.setIdUsers(object);
					tokenObjetto.setToken(token);
					itokenservicebd.guardarToken(tokenObjetto);
					
				}catch (Exception e) {
					System.out.println(e);
				}
						
			 	
		}else {
			token= "Login Incorrecto";
			mensaje = "Datos Incorrectos";
			code="400";
			object = null;
		}
		
		responseToken.put("token", token);
		responseToken.put("mensaje", mensaje);
		responseToken.put("code", code);
		responseToken.put("object", object);
		return responseToken;
		
	}
}
