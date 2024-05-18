 package com.ecommerce.produtos.api.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class ValidadorBindingResult {

  private final BindingResult bindingResult;

  public ValidadorBindingResult( BindingResult bindingResult){
    this.bindingResult = bindingResult;
  }
  
  public boolean hasErrors(){
    return bindingResult.hasErrors();
  }

  public List<String> getErrors(){
    return bindingResult.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
  }

}