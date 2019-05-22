package com.example.springback.models.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springback.clasesolas.MensajeJson;
import com.example.springback.clasesolas.SeleniumClass;



@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class SeleniumController {
	
	
	@GetMapping("/bot")
	public Map<String, String> mostrarChrome() {
		SeleniumClass selenium = new SeleniumClass();
		selenium.chromeNavegador();
		
		MensajeJson msn = new MensajeJson();
		
		return msn.mensajeString("llega", "200", "success");
	}

}
