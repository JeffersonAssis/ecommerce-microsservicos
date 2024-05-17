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

import com.ecommerce.usuarios.api.dto.ClienteDTO;
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

  @CrossOrigin(origins = "*")
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

  @CrossOrigin(origins = "*")
  @GetMapping(value = "/{cpf}")
  public ResponseEntity<ClienteDTO> buscarClienteCpf(@PathVariable("cpf") String cpf){  
    ClienteDTO cliente = clienteService.buscarClienteCpf(cpf);
    if(Objects.isNull(cliente)){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();      
    }
     return ResponseEntity.status(HttpStatus.OK).body(cliente);
  } 

  @CrossOrigin(origins = "*")
  @GetMapping()
  public ResponseEntity<List<ClienteDTO>> buscarTodosClientes(){
      return ResponseEntity.ok().body(clienteService.obterListaClientes());
  } 

  @CrossOrigin(origins = "*")
  @GetMapping(value = "paginacao")
  public ResponseEntity<Page<ClienteDTO>> buscarTodosClientesPaginado(@RequestParam(defaultValue ="0") int pagina, @RequestParam(defaultValue = "5") int quant, 
                                                                   @RequestParam(defaultValue = "id") String campoOrdenacao,@RequestParam(defaultValue = "asc") String ordenacao){
    Sort.Direction direcao = ordenacao.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable paginacao = PageRequest.of(pagina, quant, Sort.by(direcao, campoOrdenacao)); 
   //Page<Cliente> clientePaginacao = clienteService.paginacaoListaClientes(paginacao);
    return ResponseEntity.status(HttpStatus.OK).body(clienteService.paginacaoListaClientes(paginacao));
  } 

  @CrossOrigin(origins = "*")
  @GetMapping( value = "/buscarnomecontains/{nome_incase}")
  public ResponseEntity<List<ClienteDTO>> buscarClientesNome(@PathVariable("nome_incase") String nome){
    List<ClienteDTO> lClienteDTOs = clienteService.findByNomeContainsIgnoreCase(nome);
    if(Objects.isNull(lClienteDTOs))
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body(lClienteDTOs);
    
    }

  @CrossOrigin(origins = "*")
  @GetMapping( value = "/buscarnomelike/{nome}")
  public ResponseEntity<List<ClienteDTO>> buscarClientesNomeLike(@PathVariable("nome") String nome){
    List<ClienteDTO> lClienteDTOs = clienteService.findByNomeLike(nome);
    if(Objects.isNull(lClienteDTOs))
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body(lClienteDTOs);
    
    }

  @CrossOrigin(origins = "*")
  @GetMapping( value = "/buscaremail/")
  public ResponseEntity<ClienteDTO> buscarClientesEmail(@RequestParam(name = "email", required= false) String email){
    ClienteDTO lClienteDTOs = clienteService.buscarClienteEmail(email);
    if(Objects.isNull(lClienteDTOs))
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body(lClienteDTOs);
    
    }

  @CrossOrigin(origins = "*")
  @GetMapping( value = "/existeemail/")
  public ResponseEntity<String> existeClientesEmail(@RequestParam("email") String email){
    if(!clienteService.isExistClienteEmail(email))
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body("E-mail já esta cadastrado: "+ email);
    
    }
    
  @CrossOrigin(origins = "*")
  @GetMapping( value = "/buscardatanasecimento/{inicio}/{fim}")
  public ResponseEntity<List<ClienteDTO>> buscarClientesDataNascimento(@PathVariable("inicio") LocalDate inicio, @PathVariable("fim") LocalDate fim){
    List<ClienteDTO> lClienteDTOs = clienteService.findByDataNascimento(inicio, fim);
    if(Objects.isNull(lClienteDTOs))
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    
    return ResponseEntity.status(HttpStatus.OK).body(lClienteDTOs);
    
  }
  
  @CrossOrigin("*")
  @DeleteMapping("/{cpf}")
  public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable ("cpf") String cpf){
    ClienteDTO cli = clienteService.deleteCliente(cpf);
    if(Objects.isNull(cli)){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(cli);
  }

  @CrossOrigin("*")
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
