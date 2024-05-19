package com.ecommerce.produtos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String codigo;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
}
