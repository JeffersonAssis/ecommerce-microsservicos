package com.ecommerce.compras.api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
  
 
  private Long id;

  @NotNull(message = "O campo não pode ser nulo")
  @NotBlank(message = "O campo não pode ser em branco")
  @Size(min = 7, message = "O campo Email é necessario conter mais de 7 caracteres.")
  @Email
  private String emailCliente;
  
  @NotNull(message = "O campo não pode ser nulo")
  @NotBlank(message = "O campo não pode ser em branco")
  @Min(value = 1, message = "O campo quantidade é maior ou igual a 1!")
  private double total;
  
  @NotNull
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDate data;

  @NotNull(message = "Pelo menos um item tem que está na associado na compra")
  private List<ItemDTO> itensDto;

}
