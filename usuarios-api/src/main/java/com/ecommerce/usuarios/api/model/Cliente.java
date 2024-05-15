package com.ecommerce.usuarios.api.model;

import java.time.LocalDate;
import java.time.Period;

import org.hibernate.validator.constraints.br.CPF;

import com.ecommerce.usuarios.api.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "O campo nome não pode ser nulo")
  @NotBlank(message = "O campo nome não pode ser em branco")
  @Size(min = 6, message= "O nome tem que conter pelo menos 6 caracteres")
  @Pattern( regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'-]+$", message = "Nome inválido")
  @Column(name = "nome", nullable = false)
  private String nome;

  @NotNull(message = "O campo cpf não pode ser nulo")
  @NotBlank(message = "O campo cpf não pode ser em branco")
  @CPF
  @Column(nullable = false, unique = true)
  private String cpf;

  @NotNull(message = "O campo email não pode ser nulo")
  @NotBlank(message = "O campo email não pode ser em branco")
  @Email(message = "Email inválido")
  @Column(nullable = false, unique = true)
  private String email;

  @NotNull(message = "O campo telefone não pode ser nulo")
  @NotBlank(message = "O campo telefone não pode ser em branco")
  @Pattern( regexp = "^\\+?[0-9\\s-]+$")
  @Column(nullable = false)
  private String telefone;

  @Column(nullable = false)
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataNascimento;

  @Embedded
  private Endereco endereco;

  
  public ClienteDTO converterClienteDTO(){
      ClienteDTO cliDto = new ClienteDTO();
      cliDto.setId(id);
      cliDto.setNome(nome);
      cliDto.setCpf(cpf);
      Period periodo = Period.between(dataNascimento, LocalDate.now());
      cliDto.setIdade(periodo.getYears());
      cliDto.setEmail(email);
      cliDto.setTelefone(telefone);
      cliDto.setCep(endereco.getCep());
      cliDto.setLogradouro(endereco.getLogradouro());
      cliDto.setNumero(endereco.getNumero());
      cliDto.setComplemento(endereco.getComplemento());
      cliDto.setBairro(endereco.getBairro());
      cliDto.setLocalidade(endereco.getLocalidade());
      cliDto.setUf(endereco.getUf());

      return cliDto;
    }
}
