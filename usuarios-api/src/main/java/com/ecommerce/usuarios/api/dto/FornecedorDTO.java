package com.ecommerce.usuarios.api.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {

  @NotNull(message = "O campo nome fantasia não pode ser nulo")
  @NotBlank(message = "O campo nome fantasia não pode ser em vazio")
  private String nomeFantasia;

  @NotNull(message = "O campo razão social não pode ser nulo")
  @NotBlank(message = "O campo razão social não pode ser em vazio")
  private String razaoSocial;  

  @NotNull(message = "O campo CNPJ não pode ser nulo")
  @NotBlank(message = "O campo CNPJ não pode ser em vazio")
  @CNPJ
  private String cnpj;

  @NotNull(message = "O campo email não pode ser nulo")
  @NotBlank(message = "O campo email não pode ser em vazio")
  @Email(message = "Email inválido")
  private String email;
 
  @NotNull(message = "O campo telefone não pode ser nulo")
  @NotBlank(message = "O campo telefone não pode ser em vazio")
  @Pattern( regexp = "^\\+?[0-9\\s-]+$")
  private String telefone1;

  @Pattern( regexp = "^\\+?[0-9\\s-]+$")
  private String telefone2;

  @NotNull(message = "O cep é obrigatorio!")
  private String cep;

  private String logradouro;

  private String localidade;

  private String uf;

  private String bairro;

  private String numero;

  private String complemento;

}
