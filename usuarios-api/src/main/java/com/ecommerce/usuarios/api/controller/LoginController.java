package com.ecommerce.usuarios.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usuarios.api.model.Cliente;

@RestController
@RequestMapping("login")
public class LoginController {

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Cliente cliente){
      return null;
    }
}
