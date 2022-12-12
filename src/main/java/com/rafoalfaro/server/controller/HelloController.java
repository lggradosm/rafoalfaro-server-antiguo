package com.rafoalfaro.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hola");
    }
}
