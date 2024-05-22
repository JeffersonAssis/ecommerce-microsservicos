package com.ecommerce.comrpas.client.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

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

}
