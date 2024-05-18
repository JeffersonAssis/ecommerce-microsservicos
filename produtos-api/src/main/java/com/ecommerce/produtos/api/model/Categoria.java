package com.ecommerce.produtos.api.model;

import com.ecommerce.produtos.api.dto.CategoriaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="tb_categorias")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "O campo não pode ser nulo")
  @NotBlank(message = "O campo não pode ser em branco")
  @Size(min = 3, message = "Tamanho minimo do campo 3")
  @Column(nullable = false)
  private String nome;

  public CategoriaDTO convertCategoriaDTO(){
    CategoriaDTO cDto  = new CategoriaDTO();
    cDto.setNome(nome.toUpperCase());
    return cDto;
  }
}
