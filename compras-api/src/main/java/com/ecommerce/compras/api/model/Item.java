package com.ecommerce.compras.api.model;

import com.ecommerce.comrpas.client.compra.ItemDTO;
import com.ecommerce.comrpas.client.produto.ProdutoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @NotNull(message = "O campo Codigo não pode ser nulo")
    @Column(name ="codigo_produto",nullable = false)
    private String codigoProduto;
   
    @NotNull(message = "O campo quantidade não pode ser nulo")
    @Column(nullable = false)
    private int quantidade;
   
    @Column(nullable = false)
    private double preco;
    
    public ItemDTO converItem(ProdutoDTO produtoDTO){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProdutoDTO(produtoDTO);
        itemDTO.setId(id);
        itemDTO.setQuantidade(quantidade);
        itemDTO.setSubTotal(preco*quantidade);

        return itemDTO;
    }
    public Item convertTDOPaItem(ProdutoDTO produtoDTO  ){
        Item  i = new Item();
        i.setCodigoProduto(produtoDTO.getCodigo());
        i.setPreco(produtoDTO.getPreco());
        return i;
    }
}
