package com.ecommerce.compras.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.compras.api.model.Compra;
import com.ecommerce.compras.api.repository.CompraRepository;
import com.ecommerce.comrpas.client.compra.CompraDTO;

@Service
public class CompraService {
 
  private final CompraRepository compraRepository;

  @Autowired
  public CompraService(CompraRepository compraRepository){
    this.compraRepository = compraRepository;
  }

  public CompraDTO savCompra(Compra compra){
    
    return compraRepository.save(compra).converteCompra();

  }

}
