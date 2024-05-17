package com.ecommerce.usuarios.api.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usuarios.api.dto.FornecedorDTO;
import com.ecommerce.usuarios.api.model.Fornecedor;
import com.ecommerce.usuarios.api.service.FornecedorService;
import com.ecommerce.usuarios.api.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("fornecedores")
public class FornecedorController {

  private final FornecedorService fornecedorService;
 
  @Autowired
  public FornecedorController(FornecedorService fornecedorService){
    this.fornecedorService = fornecedorService;

  }

  @CrossOrigin(origins = "*")
  @PostMapping("")
  public ResponseEntity<?> saveFornecedor(@RequestBody @Valid Fornecedor fornecedor, BindingResult bindingResult){
      ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
      if(validadorBindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
      }
      try{
        return ResponseEntity.ok().body(fornecedorService.saveFornecedor(fornecedor));
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
      }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("")
  public ResponseEntity<List<FornecedorDTO>> buscarTodosFornecedores(){
      List<FornecedorDTO> l = fornecedorService.obterListFornecedor();
      if(!l.isEmpty())
       return ResponseEntity.status(HttpStatus.OK).body(l);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  } 

  @CrossOrigin(origins = "*")
  @GetMapping(value = "/paginacao")
  public ResponseEntity<Page<FornecedorDTO>> buscarTodosForncedoresPaginado(@RequestParam(defaultValue ="0") int pagina, @RequestParam(defaultValue = "5") int quant, 
                                                                   @RequestParam(defaultValue = "id") String campoOrdenacao,@RequestParam(defaultValue = "asc") String ordenacao){
    Sort.Direction direcao = ordenacao.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable paginacao = PageRequest.of(pagina, quant, Sort.by(direcao, campoOrdenacao)); 
    return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.paginacaoFornecdor(paginacao));
  } 

  @CrossOrigin(origins = "*")
  @GetMapping( value = "/nomeFantasia")
  public ResponseEntity<List<FornecedorDTO>> buscarFornecedorNomeFantasia(@RequestParam("nomeFantasia") String nomeFantasia){
    List<FornecedorDTO> fornecedorDTOs = fornecedorService.buscarFornecedorNomeFantasia(nomeFantasia);
    if(Objects.isNull(fornecedorDTOs))
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body(fornecedorDTOs);
    
    }

  @CrossOrigin(origins = "*")
  @GetMapping( value = "/buscar")
  public ResponseEntity<FornecedorDTO> buscarFonecedor(@RequestParam(name = "razao" , required = false) String razao, @RequestParam(name= "cnpj" , required = false) String cnpj, @RequestParam(name= "email" , required = false) String email){
    FornecedorDTO fDto = null;
    if(razao!=null){
      fDto = fornecedorService.buscarFornecedorRazaoSocial(razao);
      if(Objects.nonNull(fDto))
        return ResponseEntity.status(HttpStatus.OK).body(fDto);
    
    }if(cnpj!=null){
      fDto = fornecedorService.buscarFornecedorCnpj(cnpj);
      if(Objects.nonNull(fDto))
        return ResponseEntity.status(HttpStatus.OK).body(fDto);

    }if(email!=null){
      fDto = fornecedorService.buscarFornecedorEmail(email);
      if(Objects.nonNull(fDto))
        return ResponseEntity.status(HttpStatus.OK).body(fDto);

    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
  }
  
  @CrossOrigin("*")
  @DeleteMapping("/{cnpj}")
  public ResponseEntity<FornecedorDTO> deleteCliente(@PathVariable ("cnpj") String cnpj){
    FornecedorDTO forn = fornecedorService.buscarFornecedorCnpj(cnpj);
    if(Objects.isNull(forn)){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(forn);
  }

  @CrossOrigin("*")
  @PutMapping("/{cnpj}")
  public ResponseEntity<?> atulaizarCliente(@PathVariable("cnpj") String cnpj,@RequestBody @Valid Fornecedor fornecedor, BindingResult bindingResult){
    ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
      if(validadorBindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
      }
      FornecedorDTO fDto = fornecedorService.atualizarFornecedorPut(cnpj, fornecedor);
      if(Objects.isNull(fDto)){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Fornecedor não Localizado!");
      }
     return ResponseEntity.status(HttpStatus.OK).body(fDto);
  }
  
}
