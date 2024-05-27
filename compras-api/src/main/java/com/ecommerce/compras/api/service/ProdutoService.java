package com.ecommerce.compras.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.compras.api.util.SslUtil;
import com.ecommerce.comrpas.client.produto.ProdutoDTO;

@Service
public class ProdutoService {
  
  @Value("${produto.cod.url}")
  private String produtoURL;

  public ProdutoDTO buscarProduto(String codigo){
    RestTemplate restTemplate = SslUtil.getRestTemplate();
    String url = produtoURL;
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).path(codigo);
    ResponseEntity<ProdutoDTO> response = restTemplate.getForEntity(builder.toUriString(),ProdutoDTO.class);
    return response.getBody();
  }


}
