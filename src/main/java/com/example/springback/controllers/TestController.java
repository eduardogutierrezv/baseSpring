package com.example.springback.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/test/hola")
public class TestController {

    @PostMapping
    public String HollaMundo() {

        try {
            System.err.println("Hola");

            return "Hola";
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("hola pero malo");
            return "Hola pero malo";
        }

    }
}