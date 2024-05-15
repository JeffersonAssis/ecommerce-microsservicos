package com.ecommerce.produtos.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
  
  @NotNull(message = "O campo não pode ser nulo")
  @NotBlank(message = "O campo não pode ser em branco")
  @Min(value = 1, message = "O campo quantidade é maior ou igual a 1!")
  private String nome;
  
}
