package com.example.springback.models.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springback.clasesolas.MensajeJson;
import com.example.springback.clasesolas.PruebaJsoup;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pruebajsoup/{url}")
public class JoupService {
	
	PruebaJsoup pJsoup = new PruebaJsoup();
	
	@GetMapping
	public Map<String, String>pruebaJsoup(@PathVariable String url){
		MensajeJson msnJson = new MensajeJson();
		pJsoup.extraW("https://"+url);
		System.out.println("paso");
		return msnJson.mensajeString("del jooupService", "https://"+url, "success");
	}
}
