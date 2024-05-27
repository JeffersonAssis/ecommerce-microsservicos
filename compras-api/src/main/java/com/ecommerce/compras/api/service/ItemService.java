package com.ecommerce.compras.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.compras.api.model.Item;
import com.ecommerce.compras.api.repository.ItemRepository;
import com.ecommerce.comrpas.client.compra.ItemDTO;
import com.ecommerce.comrpas.client.produto.ProdutoDTO;



@Service
public class ItemService {
  
  private final ItemRepository itemRepository;

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  public ItemService(ItemRepository itemRepository){
    this.itemRepository=itemRepository;
  }
  
public List<ItemDTO> saveItem(List<Item> item){

  List<ItemDTO> itemDTOs = item.stream().map(i-> {
    ProdutoDTO produtoDTO = produtoService.buscarProduto(i.getCodigoProduto());
    i.setPreco(produtoDTO.getPreco());
    return i.converItem(produtoDTO); 
}).collect(Collectors.toList());

  itemRepository.saveAll(item);
  return itemDTOs;
}

public Item save(Item i) {
  ProdutoDTO produtoDTO = produtoService.buscarProduto(i.getCodigoProduto());
  i.setPreco(produtoDTO.getPreco());
  return itemRepository.save(i);

}


public List<ItemDTO> convertLisDto(List<Item> listItem){
  return listItem.stream().map(i -> {
      ProdutoDTO pDto = produtoService.buscarProduto(i.getCodigoProduto());
      return i.converItem(pDto);
  }).collect(Collectors.toList());
  
}

public Item findById(Long id){
  Optional<Item> itemOp = itemRepository.findById(id);
  if(itemOp.isPresent()){
    return itemOp.get();
  }
  return null;
}

}
