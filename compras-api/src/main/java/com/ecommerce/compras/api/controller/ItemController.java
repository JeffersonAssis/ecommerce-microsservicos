package com.ecommerce.compras.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.compras.api.model.Item;
import com.ecommerce.compras.api.service.ItemService;
import com.ecommerce.compras.api.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("itens")
public class ItemController {
  
  private final ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService){
    this.itemService = itemService;
  }

  @CrossOrigin("*")
  @PostMapping("save")
  public ResponseEntity<?> save(@RequestBody @Valid Item item, BindingResult bindingResult){
      ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
      if(validadorBindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
      }
      try{
        return ResponseEntity.ok().body(itemService.save(item));
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar a compra "+ e.getMessage());
      }
  }

  
}
