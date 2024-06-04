package com.ecommerce.compras.api.dto;

import com.ecommerce.compras.api.model.Compra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraAuthDTO {
    
  private Compra compra;
  
  private String token;
}
