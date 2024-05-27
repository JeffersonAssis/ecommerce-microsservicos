package com.ecommerce.produtos.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.ecommerce.comrpas.client.produto.ProdutoDTO;
import com.ecommerce.produtos.api.model.Produto;
import com.ecommerce.produtos.api.service.ProdutoService;
import com.ecommerce.produtos.api.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("produtos")
public class ProdutoController {
  
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService){
      this.produtoService = produtoService;
    }

    
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

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoDTO> buscarCodigo(@PathVariable("codigo") String codigo){
     ProdutoDTO pDto = produtoService.findByCodigo(codigo);
     if(Objects.nonNull(pDto))
      return ResponseEntity.status(HttpStatus.OK).body(pDto);

      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
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


  @DeleteMapping("delete/{codigo}")
  public ResponseEntity<?> categoriaDelete(@PathVariable("codigo") String codigo){
    ProdutoDTO pDto = produtoService.produtoDelete(codigo);
    if(Objects.nonNull(pDto)){
      return ResponseEntity.status(HttpStatus.OK).body("Categoria excluida com sucesso:\n"+ pDto.getNome());
    } 
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria não cadastrada!");
  }

 
  @GetMapping("/")
  public ResponseEntity<List<ProdutoDTO>> listaProdutos(){
    List<ProdutoDTO> listpDto = produtoService.listarProdutoDTOs();
    if(Objects.nonNull(listpDto))
    return ResponseEntity.status(HttpStatus.OK).body(listpDto);

   return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<ProdutoDTO>> listaProdutos(@RequestParam(name="menorPreco", required = false, defaultValue= "0") double menorPreco,
  @RequestParam(name="maiorPreco", required =false, defaultValue = "0") double maiorPreco, @RequestParam(name ="nomeProd" , required = false) String nomeProd,
  @RequestParam(name = "catProd" , required = false) String catProd){
    
    List<ProdutoDTO> lProdutoDTOs; 
   
    if(nomeProd!=null && !nomeProd.isEmpty()){
      lProdutoDTOs = produtoService.findByNomeLike(nomeProd);
    }else if(menorPreco > 0 && maiorPreco == 0 ){
      lProdutoDTOs = produtoService.findByMenorPreco(menorPreco);
    }else if(maiorPreco > 0 && menorPreco == 0){
      lProdutoDTOs = produtoService.findByMaiorPreco(maiorPreco);
    }else if(menorPreco > 0 && maiorPreco > 0){
       lProdutoDTOs = produtoService.findByDiferencaPreco(menorPreco, maiorPreco);
    }else if(catProd!=null){
      lProdutoDTOs = produtoService.findByCategoria(catProd);
    }else{
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }                                                      
    return ResponseEntity.status(HttpStatus.OK).body(lProdutoDTOs);
  }

  
  @GetMapping("/pagebuscar")
  public ResponseEntity<Page<ProdutoDTO>> listaProdutosPaginacao(@RequestParam(name="menorPreco", defaultValue = "0", required = false) Double menorPreco,
  @RequestParam(name="maiorPreco", required =false ,defaultValue = "0") Double maiorPreco, @RequestParam(name ="nomeProd" , required = false) String nomeProd,
  @RequestParam(name = "catProd" , required = false) String catProd, @RequestParam(name="pagina", defaultValue = "0") int pagina,
  @RequestParam(name="size", defaultValue = "5") int size, @RequestParam(name="campsort", defaultValue = "preco") String campsort,
  @RequestParam(name="ordenacao", defaultValue = "asc") String ordenacao) {
        
      Sort.Direction direcao = ordenacao.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
      Pageable paginacao = PageRequest.of(pagina, size, Sort.by(direcao, campsort));
      Page<ProdutoDTO> produtosPaginados;

      if (nomeProd != null && !nomeProd.isEmpty()) {
          produtosPaginados = produtoService.findByNomeLike(nomeProd, paginacao);
      }else if(menorPreco > 0 && maiorPreco == 0) {
          produtosPaginados = produtoService.findByMenorPreco(menorPreco, paginacao);
      }else if(maiorPreco > 0 && menorPreco == 0) {
          produtosPaginados = produtoService.findByMaiorPreco(maiorPreco, paginacao);
      }else if(catProd != null && !catProd.isEmpty()) {
          produtosPaginados = produtoService.findByCategoria(catProd, paginacao);
      }else{
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }

      return ResponseEntity.status(HttpStatus.OK).body(produtosPaginados);
  }

}
