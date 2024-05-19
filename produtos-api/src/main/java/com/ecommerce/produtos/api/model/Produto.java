package com.ecommerce.produtos.api.model;

import com.ecommerce.produtos.api.dto.ProdutoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique = true)
    private String codigo;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Size(min = 3)
    @Column(nullable=false)
    private String nome;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode ser em branco")
    @Column(nullable=false, columnDefinition = "TEXT")
    private String descricao;
    
    @NotNull(message = "O campo não pode ser nulo")
    @Min(1)   
    @Column(nullable=false)
    private double preco;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id",  nullable = false)
    private Categoria categoria;

    public ProdutoDTO conveProdutoDTO(){
        ProdutoDTO pDto= new ProdutoDTO();
        pDto.setCategoria(categoria.getNome());
        pDto.setCodigo(codigo);
        pDto.setDescricao(descricao);
        pDto.setNome(nome);
        pDto.setPreco(preco);
        pDto.setId(id);
        return pDto;
    }
}
