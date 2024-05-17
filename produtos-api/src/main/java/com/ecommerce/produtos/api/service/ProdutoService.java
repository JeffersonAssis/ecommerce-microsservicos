package com.ecommerce.produtos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.produtos.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
  
  private final ProdutoRepository produtoRepository;

  @Autowired
  public ProdutoService(ProdutoRepository produtoRepository){
    this.produtoRepository = produtoRepository;
  }

  
}
