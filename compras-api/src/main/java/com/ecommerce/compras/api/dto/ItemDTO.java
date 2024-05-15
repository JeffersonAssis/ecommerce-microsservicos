package com.ecommerce.compras.api.dto;

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
public class ItemDTO {
   
    @NotNull(message = "O campo não pode ser Nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Size(min = 3)
    private String codigoProduto;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Min(value = 1, message = "O campo quantidade é maior ou igual a 1!")
    private int quantidade;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Min(value = 1, message = "O campo quantidade é maior ou igual a 1!")
    private double preco;

}
