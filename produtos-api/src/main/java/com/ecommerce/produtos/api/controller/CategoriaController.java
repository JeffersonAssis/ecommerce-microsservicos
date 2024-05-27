package com.ecommerce.produtos.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.comrpas.client.produto.CategoriaDTO;
import com.ecommerce.produtos.api.model.Categoria;
import com.ecommerce.produtos.api.service.CategoriaService;
import com.ecommerce.produtos.api.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("categorias")
public class CategoriaController {
  
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService){
      this.categoriaService = categoriaService;
    }

    
    @PostMapping("")
    public ResponseEntity<?> categoriaSave(@RequestBody @Valid Categoria categoria, BindingResult bindingResult ){
       ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
       if(validadorBindingResult.hasErrors()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validadorBindingResult.getErrors());
       }
       try {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.categoriaSave(categoria)); 
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }
    }

 
  @DeleteMapping("delete/{nome}")
  public ResponseEntity<?> categoriaDelete(@PathVariable("nome") String nome){
    CategoriaDTO cDto = categoriaService.categoiraDelete(nome);
    if(Objects.nonNull(cDto)){
      return ResponseEntity.status(HttpStatus.OK).body("Categoria excluida com sucesso:\n"+ cDto.getNome());
    } 
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria não cadastrada!");
  }

 
  @GetMapping("/")
  public ResponseEntity<List<CategoriaDTO>> listaCategorias(){
    List<CategoriaDTO> listcDto = categoriaService.listarCategorias();
    if(Objects.nonNull(listcDto))
    return ResponseEntity.status(HttpStatus.OK).body(listcDto);

   return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

}
