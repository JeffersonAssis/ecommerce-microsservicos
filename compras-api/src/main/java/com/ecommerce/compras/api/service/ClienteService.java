package com.ecommerce.compras.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.compras.api.util.SslUtil;
import com.ecommerce.comrpas.client.usuario.ClienteDTO;

@Service
public class ClienteService {

    public ClienteDTO buscarCliente(String email){
      RestTemplate restTemplate = SslUtil.getRestTemplate();
      String url = "https://localhost:8080/clientes/email/";
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("email", email);
      ResponseEntity<ClienteDTO> response = restTemplate.getForEntity(builder.toUriString(),ClienteDTO.class);
      return response.getBody();
    }

}
