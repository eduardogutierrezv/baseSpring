package com.example.springback.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hola")
public class TestController {

    @GetMapping()
    public String HollaMundo() {
        try {
            System.err.println("hola");
        } catch (Exception e) {
            System.err.println("hola pero malo");
        }
        return "Hola";

    }

}
