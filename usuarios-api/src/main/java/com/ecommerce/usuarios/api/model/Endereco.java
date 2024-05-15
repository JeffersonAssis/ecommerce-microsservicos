package com.ecommerce.usuarios.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

   @NotNull(message = "O cep precisa ser Informado!")
   private String cep;
   private String logradouro;
   @Column(name="cidade")
   private String localidade;
   private String uf;
   private String bairro;
   private String numero;
   private String complemento;
   
  
   


}
