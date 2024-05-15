package com.ecommerce.produtos.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Size(min = 3)
    private String codigo;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Size(min = 3)
    private String nome;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Size(min = 7)
    private String descricao;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Size(min = 1)
    private double preco;
    
    private CategoriaDTO categoriadto;
}
