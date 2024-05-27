package com.ecommerce.comrpas.client.compra;

import com.ecommerce.comrpas.client.produto.ProdutoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
   
    
    private Long id;

    private ProdutoDTO produtoDTO;
   
    private int quantidade;

}
