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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.produtos.api.dto.ProdutoDTO;
import com.ecommerce.produtos.api.model.Produto;
import com.ecommerce.produtos.api.service.ProdutoService;
import com.ecommerce.produtos.api.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
  
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService){
      this.produtoService = produtoService;
    }

    @CrossOrigin("*")
    @PostMapping("")
    public ResponseEntity<?> produtoSave(@RequestBody @Valid Produto produto, BindingResult bindingResult ){
       ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
       if(validadorBindingResult.hasErrors()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validadorBindingResult.getErrors());
       }
       try {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.produtoSave(produto)); 
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }
    }

    @CrossOrigin("*")
    @PostMapping("listaProdutos/")
    public ResponseEntity<?> produtoListSave(@RequestBody @Valid List<Produto> produto, BindingResult bindingResult ){
       ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
       if(validadorBindingResult.hasErrors()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validadorBindingResult.getErrors());
       }
       try {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.produtoSaveMuitos(produto)); 
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }
    }

  @CrossOrigin("*")
  @DeleteMapping("delete/{codigo}")
  public ResponseEntity<?> categoriaDelete(@PathVariable("codigo") String codigo){
    ProdutoDTO pDto = produtoService.produtoDelete(codigo);
    if(Objects.nonNull(pDto)){
      return ResponseEntity.status(HttpStatus.OK).body("Categoria excluida com sucesso:\n"+ pDto.getNome());
    } 
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria não cadastrada!");
  }

  @CrossOrigin("*")
  @GetMapping("/")
  public ResponseEntity<List<ProdutoDTO>> listaProdutos(){
    List<ProdutoDTO> listpDto = produtoService.listarProdutoDTOs();
    if(Objects.nonNull(listpDto))
    return ResponseEntity.status(HttpStatus.OK).body(listpDto);

   return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

  @CrossOrigin("*")
  @GetMapping("/buscar")
  public ResponseEntity<List<ProdutoDTO>> listaProdutos(@RequestParam(name="menorPreco", required = false, defaultValue= "0") double menorPreco,
                                                        @RequestParam(name="maiorPreco", required =false, defaultValue = "0") double maiorPreco,
                                                        @RequestParam(name ="nomeProd" , required = false) String nomeProd){
    if(nomeProd!=null){
      List<ProdutoDTO> lProdutoDTOs = produtoService.findByNomeLike(nomeProd);
      if(Objects.nonNull(lProdutoDTOs))
        return ResponseEntity.status(HttpStatus.OK).body(lProdutoDTOs);
    }else if(menorPreco > 0 && maiorPreco == 0 ){
      List<ProdutoDTO> lProdutoDTOs = produtoService.findByMenorPreco(menorPreco);
      if(Objects.nonNull(lProdutoDTOs))
        return ResponseEntity.status(HttpStatus.OK).body(lProdutoDTOs);
    }else if(maiorPreco > 0 && menorPreco == 0){
      List<ProdutoDTO> lProdutoDTOs = produtoService.findByMaiorPreco(maiorPreco);
      if(Objects.nonNull(lProdutoDTOs))
        return ResponseEntity.status(HttpStatus.OK).body(lProdutoDTOs);
    }else if(menorPreco > 0 && maiorPreco > 0){
      List<ProdutoDTO> lProdutoDTOs = produtoService.findByDiferencaPreco(menorPreco, maiorPreco);
      if(Objects.nonNull(lProdutoDTOs))
        return ResponseEntity.status(HttpStatus.OK).body(lProdutoDTOs);
    }
                                                          
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }


}
