package com.ecommerce.usuarios.api.model;

import com.ecommerce.comrpas.client.usuario.FornecedorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "tb_fornecedores")
public class Fornecedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "O campo Nome Fantasia não pode ser nulo")
  @NotBlank(message = "O campo Nome Fantasia não pode ser em vazio")
  @Column(nullable = false)
  private String nomeFantasia;

  @NotNull(message = "O campo Razão Social não pode ser nulo")
  @NotBlank(message = "O campo Razão Social não pode ser em vazio")
  @Column( nullable = false , unique = true)
  private String razaoSocial;  

  @NotNull(message = "O campo CNPJ não pode ser nulo")
  @NotBlank(message = "O campo CNPJ não pode ser em vazio")
  @Column(nullable = false, unique = true)
  private String cnpj;

  @NotNull(message = "O campo Email não pode ser nulo")
  @NotBlank(message = "O campo Email não pode ser em vazio")
  @Email(message ="Email inválido")
  @Column(nullable = false, unique = true)
  private String email;
 
  @NotNull(message = "O campo telefone não pode ser nulo")
  @NotBlank(message = "O campo telefone não pode ser em vazio")
  @Pattern( regexp = "^\\+?[0-9\\s-]+$")
  @Column(nullable = false)
  private String telefone1;

  @Pattern( regexp = "^\\+?[0-9\\s-]+$")
  private String telefone2;

  @Embedded
  private Endereco endereco;

  public FornecedorDTO convertFornecedorDTO(){
    FornecedorDTO fDto = new FornecedorDTO();
    fDto.setCnpj(cnpj);
    fDto.setBairro(endereco.getBairro());
    fDto.setCep(endereco.getCep());
    fDto.setComplemento(endereco.getComplemento());
    fDto.setEmail(email);
    fDto.setLocalidade(endereco.getLocalidade());
    fDto.setLogradouro(endereco.getLogradouro());
    fDto.setNumero(endereco.getNumero());
    fDto.setRazaoSocial(razaoSocial);
    fDto.setNomeFantasia(nomeFantasia);
    fDto.setTelefone1(telefone1);
    fDto.setTelefone2(telefone2);
    fDto.setUf(endereco.getUf());
    return fDto;
  }

}
