package com.ecommerce.usuarios.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.comrpas.client.usuario.*;
import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.model.Endereco;
import com.ecommerce.usuarios.api.repository.ClienteRepository;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;
  
  @Autowired
  private EnderecoService enderecoService;

  @Autowired
  private ConsultaCep consultaCep;

  @Autowired
  private AuthorizationService authorizationService;

  @Autowired
  public ClienteService (ClienteRepository clienteRepository){
    this.clienteRepository = clienteRepository;
  
  }


  public ClienteDTO save(Cliente cliente){
    
    String Senha = cliente.getSenha();
    BCryptPasswordEncoder encoder = authorizationService.getCryptPasswordEncoder();
    cliente.setSenha(encoder.encode(Senha));

    EnderecoDTO enderecoDTO = enderecoService.buscaEnderecoDTO(cliente.getEndereco().getCep());
    Endereco end = new Endereco();
    end.setBairro(enderecoDTO.getBairro());
    end.setLocalidade(enderecoDTO.getLocalidade());
    end.setLogradouro(enderecoDTO.getLogradouro());
    end.setUf(enderecoDTO.getUf());
    end.setComplemento(enderecoDTO.getComplemento());
    end.setCep(cliente.getEndereco().getCep());
    end.setNumero(cliente.getEndereco().getNumero());
    cliente.setEndereco(end);

    return clienteRepository.save(cliente).converterClienteDTO();

  }

  public ClienteDTO saveCliente(Cliente cliente){
    String Senha = cliente.getSenha();
    BCryptPasswordEncoder encoder = authorizationService.getCryptPasswordEncoder();
    cliente.setSenha(encoder.encode(Senha));
    String cep = cliente.getEndereco().getCep();
    String numero = cliente.getEndereco().getNumero();
    Optional<Endereco> end = consultaCep.viaCep(cep);
    if(end.isPresent()){ 
      cliente.setEndereco(end.get());
      cliente.getEndereco().setNumero(numero);
     return clienteRepository.save(cliente).converterClienteDTO();
  }else{
    return null;
  }

  }

  public ClienteDTO buscarClienteEmail (String email){
   Optional<Cliente> cliOpt= clienteRepository.findByEmail(email);
    if(cliOpt.isPresent())
      return cliOpt.get().converterClienteDTO();
   
    return null;
    
  }
  
  public Cliente buscarClienteCpfA (String cpf){
    Optional<Cliente> cliOp =  clienteRepository.findByCpf(cpf);
      if(cliOp.isPresent()){
        return cliOp.get();  
      }      
          
      return null;  
    }

  public ClienteDTO buscarClienteCpf (String cpf){
    Optional<Cliente> cliOp =  clienteRepository.findByCpf(cpf);
      if(cliOp.isPresent()){
        return cliOp.get().converterClienteDTO();  
      }      
          
      return null;  
    }

  public  List<ClienteDTO> obterListaClientes(){
    return clienteRepository.findAll()
                            .stream()
                            .map(c -> c.converterClienteDTO())
                            .collect(Collectors.toList());
  }

  public Page<ClienteDTO> paginacaoListaClientes(Pageable paginacao){
    return clienteRepository.findAll(paginacao).map(Cliente::converterClienteDTO);
  }

  public List<ClienteDTO> findByNomeContainsIgnoreCase(String nome){
    Optional<List<Cliente>> listCliOp = clienteRepository.findByNomeContainsIgnoreCase(nome);
    if(listCliOp.isPresent()){
      List<Cliente> listcliente = listCliOp.get();
      return listcliente.stream()
                        .map(c -> c.converterClienteDTO())
                        .collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public List<ClienteDTO> findByNomeLike(String nome){
    Optional<List<Cliente>> listCliOp = clienteRepository.findByNomeLike("%"+nome+"%");
    if(listCliOp.isPresent()){
      List<Cliente> listcliente = listCliOp.get();
      return listcliente.stream().map(c -> c.converterClienteDTO()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }
  
  public List<ClienteDTO> findByDataNascimento(LocalDate inicio, LocalDate fim){
    Optional<List<Cliente>> listCliOp = clienteRepository.findByDataNascimentoBetween(inicio, fim);
    if(listCliOp.isPresent()){
      List<Cliente> listcliente = listCliOp.get();
      return listcliente.stream().map(c -> c.converterClienteDTO()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public boolean isExistClienteEmail (String email){
     return clienteRepository.existsByEmail(email);    
   }
  
  public ClienteDTO deleteCliente(String cpf){
    Optional<Cliente> cliOp = clienteRepository.findByCpf(cpf);
    if(cliOp.isPresent()){
      Cliente cli = cliOp.get();
      clienteRepository.delete(cli);
      return cli.converterClienteDTO();
    }
    return null;
    
  }  

  public ClienteDTO atualizarClientePut(String cpf, Cliente cliente){
    try {
        Optional<Cliente> cliOp = clienteRepository.findByCpf(cpf);
        if(!cliOp.isPresent()){
          return null;      
        }
        Cliente cli = cliOp.get();
        cliente.setId(cli.getId());
        return  saveCliente(cliente);
        
      } catch (Exception e) {
        return null;
      }
  }

  
}
