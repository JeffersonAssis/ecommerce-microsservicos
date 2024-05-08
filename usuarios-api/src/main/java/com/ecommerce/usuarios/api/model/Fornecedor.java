package com.ecommerce.usuarios.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @Column(nullable = false)
  private String nomeFantasia;

  @Column( nullable = false , unique = true)
  private String razaoSocial;  

  @Column(nullable = false, unique = true)
  private String cnpj;

  @Column(nullable = false, unique = true)
  private String email;
 
  @Column(nullable = false)
  private String telefone;

  @Embedded
  private Endereco endereco;

}
