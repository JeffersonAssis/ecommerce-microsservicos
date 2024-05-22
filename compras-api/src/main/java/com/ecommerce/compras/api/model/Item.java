package com.ecommerce.compras.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_itens")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Column(nullable = false)
    private String codigoProduto;
   
    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Column(nullable = false)
    private int quantidade;
   
    @NotNull(message = "O campo não pode ser nulo")
    @Column(nullable = false)
    private double preco;

}
