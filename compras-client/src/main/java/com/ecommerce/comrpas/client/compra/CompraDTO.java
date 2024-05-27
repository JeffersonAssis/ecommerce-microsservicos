package com.ecommerce.comrpas.client.compra;

import java.time.LocalDate;
import java.util.List;

import com.ecommerce.comrpas.client.usuario.ClienteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
 
  private Long id;

  private ClienteDTO clienteDTO;

  private double total;
  
  private LocalDate data;

  private List<ItemDTO> itensDto;

}
