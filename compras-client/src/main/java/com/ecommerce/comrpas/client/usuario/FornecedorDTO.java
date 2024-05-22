package com.ecommerce.comrpas.client.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {


  private String nomeFantasia;

  private String razaoSocial;  

  private String cnpj;

  private String email;
 
  private String telefone1;

  private String telefone2;
 
  private String cep;

  private String logradouro;

  private String localidade;

  private String uf;

  private String bairro;

  private String numero;

  private String complemento;

}
