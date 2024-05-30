package com.ecommerce.usuarios.api.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.comrpas.client.usuario.ClienteDTO;
import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.service.ClienteService;
import com.ecommerce.usuarios.api.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

  private final ClienteService clienteService;
  

  @Autowired
  public ClienteController(ClienteService clienteService){
    this.clienteService = clienteService;

  }

  @PostMapping("")
  public ResponseEntity<?> saveCliente(@RequestBody @Valid Cliente cliente, BindingResult bindingResult){
      
      ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
      if(validadorBindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
      }
      try{
        
        return ResponseEntity.ok().body(clienteService.saveCliente(cliente));
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o cliente "+ e);
      }
  }

 
  @PostMapping("save")
  public ResponseEntity<?> save(@RequestBody @Valid Cliente cliente, BindingResult bindingResult){
      ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
      if(validadorBindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
      }
      try{
        return ResponseEntity.ok().body(clienteService.save(cliente));
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o cliente "+ e);
      }
  }


  @GetMapping(value = "/{cpf}")
  public ResponseEntity<ClienteDTO> buscarClienteCpf(@PathVariable("cpf") String cpf){  
    ClienteDTO cliente = clienteService.buscarClienteCpf(cpf);
    if(Objects.isNull(cliente)){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();      
    }
     return ResponseEntity.status(HttpStatus.OK).body(cliente);
  } 

  
  @GetMapping(value = "paginacao")
  public ResponseEntity<Page<ClienteDTO>> buscarTodosClientesPaginado(@RequestParam(defaultValue ="0") int pagina, @RequestParam(defaultValue = "5") int quant, 
  @RequestParam(defaultValue = "id") String campoOrdenacao,@RequestParam(defaultValue = "asc") String ordenacao){
    
    Sort.Direction direcao = ordenacao.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable paginacao = PageRequest.of(pagina, quant, Sort.by(direcao, campoOrdenacao)); 
    return ResponseEntity.status(HttpStatus.OK).body(clienteService.paginacaoListaClientes(paginacao));
  } 

 
  @GetMapping( value = "/buscar")
  public ResponseEntity<List<ClienteDTO>> buscarClientesNomeLike(@RequestParam( name = "nome", required = false) String nome,
  @RequestParam(name= "inicio", required = false) LocalDate inicio, @RequestParam(name= "fim", required = false) LocalDate fim,
  @RequestParam(name= "nomeIgCase", required = false) String nomeIgCase){
    List<ClienteDTO> lClienteDTOs;
   
    if(nome != null && !nome.isEmpty()){
      lClienteDTOs = clienteService.findByNomeLike(nome);
    }else if(inicio != null && fim != null){
      lClienteDTOs = clienteService.findByDataNascimento(inicio, fim);
    }else if(nomeIgCase != null && !nomeIgCase.isEmpty()){
      lClienteDTOs = clienteService.findByNomeContainsIgnoreCase(nomeIgCase);
    }else{
      lClienteDTOs = clienteService.obterListaClientes();
    }
    
    if(!lClienteDTOs.isEmpty())
      return ResponseEntity.status(HttpStatus.OK).body(lClienteDTOs);
    
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

  @GetMapping( value = "/email/")
  public ResponseEntity<ClienteDTO> buscarClientesEmail(@RequestParam("email") String email){
    ClienteDTO lClienteDTOs = clienteService.buscarClienteEmail(email);
    if(Objects.isNull(lClienteDTOs))
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body(lClienteDTOs);
    
    }

  @GetMapping( value = "/existeemail/")
  public ResponseEntity<String> existeClientesEmail(@RequestParam("email") String email){
    if(!clienteService.isExistClienteEmail(email))
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body("E-mail já esta cadastrado: "+ email);
    
    }

  @DeleteMapping("/{cpf}")
  public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable ("cpf") String cpf){
    ClienteDTO cli = clienteService.deleteCliente(cpf);
    if(Objects.isNull(cli)){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(cli);
  }

  @PutMapping("/{cpf}")
  public ResponseEntity<?> atulaizarCliente(@PathVariable("cpf") String cpf,@RequestBody @Valid Cliente cliente, BindingResult bindingResult){
    ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
      if(validadorBindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
      }
      ClienteDTO clienteDTO = clienteService.atualizarClientePut(cpf, cliente);
      if(Objects.isNull(clienteDTO)){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente não Localizado!");
      }
     return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
  }
  
}
