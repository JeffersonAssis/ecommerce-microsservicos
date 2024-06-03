package com.ecommerce.compras.api.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.compras.api.util.SslUtil;
import com.ecommerce.comrpas.client.usuario.ClienteDTO;

@Service
public class ClienteService {

  
    public ClienteDTO buscarCliente(String email, String token){
      RestTemplate restTemplate = SslUtil.getRestTemplate();
      String url = "https://localhost:8080/clientes/email/";

      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "Bearer " + token); 

      HttpEntity<String> entity = new HttpEntity<>(headers);

      UriComponentsBuilder builder = UriComponentsBuilder
              .fromHttpUrl(url)
              .queryParam("email", email);

      ResponseEntity<ClienteDTO> response = restTemplate.exchange(
              builder.toUriString(), 
              HttpMethod.GET,
              entity,
              ClienteDTO.class
      );
      return response.getBody();
    }

}
