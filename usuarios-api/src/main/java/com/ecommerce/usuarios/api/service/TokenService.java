package com.ecommerce.usuarios.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.usuarios.api.model.Cliente;

@Service
public class TokenService {

  public String generateToken(Cliente cliente){

    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    return JWT.create()
              .withIssuer(TOKEN_ISSUER)
              .withSubject(cliente.getCpf())
              .withExpiresAt(_exprirationData())
              .withClaim("id", cliente.getId())
              .sign(algorithm);
  }

  public String getSubject(String token){
   try{
    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    return JWT.require(algorithm)
              .withIssuer(TOKEN_ISSUER)
              .build()
              .verify(token)
              .getSubject();
   }catch(Exception e){
    throw new RuntimeException("Token invalido");
   }
    
  }

  private Instant _exprirationData(){
    return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
  }
  
  @Value("${spring.security.sercret-key}")
  private String SECRET_KEY;

  @Value("${spring.security.token-issuer}")
  private String TOKEN_ISSUER;
  
}
