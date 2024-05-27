package com.ecommerce.compras.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.compras.api.model.Compra;
import com.ecommerce.compras.api.service.CompraService;
import com.ecommerce.compras.api.util.ValidadorBindingResult;
import com.ecommerce.comrpas.client.compra.CompraDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("compras")
public class CompraController {
  
  private final CompraService compraService;

  @Autowired
  public CompraController(CompraService compraService){
    this.compraService = compraService;
  }

  @CrossOrigin("*")
  @PostMapping("save")
  public ResponseEntity<?> save(@RequestBody @Valid Compra compra, BindingResult bindingResult){
      ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
      if(validadorBindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
      }
      try{
        return ResponseEntity.ok().body(compraService.saveCompra(compra));
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar a compra "+ e.getMessage());
      }
  }

  @CrossOrigin("*")
  @GetMapping("/")
  public ResponseEntity<List<CompraDTO>> buscarTodasCompras(){
    List<CompraDTO> list = compraService.obterTodasCompras();
    if(Objects.nonNull(list))
      return ResponseEntity.status(HttpStatus.OK).body(list);

     return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  } 

  @CrossOrigin("*")
  @GetMapping("/buscar")
  public ResponseEntity<List<CompraDTO>> buscarTodasComprasPorParamentros(@RequestParam(value = "email", required = false)String email, @RequestParam(value = "inicio", required = false)LocalDate inicio, @RequestParam(value = "fim", required = false)LocalDate fim){
    List<CompraDTO> list = null;
    if(email != null && !email.isEmpty()){
      list = compraService.obterTodasComprasEmail(email);
    }else if(inicio != null && fim !=null){
      list = compraService.obterTodasComprasData(inicio, fim);
    }
    if(Objects.nonNull(list))
      return ResponseEntity.status(HttpStatus.OK).body(list);

     return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  } 
}
