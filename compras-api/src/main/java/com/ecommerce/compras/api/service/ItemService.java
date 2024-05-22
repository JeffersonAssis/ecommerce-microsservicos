package com.ecommerce.compras.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.compras.api.repository.ItemRepository;

@Service
public class ItemService {
  
  private final ItemRepository itemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository){
    this.itemRepository=itemRepository;
  }
  
}
