package com.ecommerce.produtos.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique = true)
    private String codigo;
    
    @Column(nullable=false)
    private String nome;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable=false)
    private double preco;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;
}
