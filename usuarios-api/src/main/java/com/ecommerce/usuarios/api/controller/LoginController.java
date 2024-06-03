package com.ecommerce.usuarios.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.comrpas.client.usuario.LoginDTO;
import com.ecommerce.comrpas.client.usuario.UsuarioDTO;
import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.service.TokenService;


@RestController
@RequestMapping("login")
public class LoginController {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private AuthenticationManager authenticationManager;

    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO login){
      try {
          Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha()));

          Cliente cliente = (Cliente) authentication.getPrincipal();
          
          String Token = tokenService.generateToken(cliente);

          UsuarioDTO usuarioDTO = new UsuarioDTO(cliente.converterClienteDTO(), Token);

          return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
          
      } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
     
    }
}
