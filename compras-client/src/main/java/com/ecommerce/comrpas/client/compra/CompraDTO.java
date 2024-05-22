package com.ecommerce.comrpas.client.compra;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
 
  private Long id;

  private String nome;
  private String cpf;
  private String email;
  private String telefone;
  private int idade;
  private String cep;
  private String logradouro;
  private String localidade;
  private String uf;
  private String bairro;
  private String numero;
  private String complemento;

  private double total;
  
  private LocalDate data;

  private List<ItemDTO> itensDto;

}
