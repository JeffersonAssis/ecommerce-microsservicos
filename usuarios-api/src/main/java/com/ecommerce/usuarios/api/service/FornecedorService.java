package com.ecommerce.usuarios.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.usuarios.api.dto.FornecedorDTO;
import com.ecommerce.usuarios.api.model.Endereco;
import com.ecommerce.usuarios.api.model.Fornecedor;
import com.ecommerce.usuarios.api.repository.FornecedorRepository;

@Service
public class FornecedorService {

  @Autowired
  private ConsultaCep consultaCep;

  private final FornecedorRepository fornecedorRepository;


  @Autowired
  public FornecedorService(FornecedorRepository fornecedorRepository ){
    this.fornecedorRepository = fornecedorRepository;
    
  }

   public FornecedorDTO saveFornecedor(Fornecedor fornecedor){
    String cep = fornecedor.getEndereco().getCep();
    String numero = fornecedor.getEndereco().getNumero();
    Optional<Endereco> end = consultaCep.viaCep(cep);
    if(end.isPresent()){ 
      fornecedor.setEndereco(end.get());
      fornecedor.getEndereco().setNumero(numero);
     return fornecedorRepository.save(fornecedor).convertFornecedorDTO();
  }else{
    return null;
  }

  }

  public FornecedorDTO buscarFornecedorEmail (String email){
   Optional<Fornecedor> forOpt= fornecedorRepository.findByEmail(email);
    if(forOpt.isPresent())
      return forOpt.get().convertFornecedorDTO();
   
    return null;
    
  }

  public FornecedorDTO buscarFornecedorCnpj (String cnpj){
    Optional<Fornecedor> forOpt= fornecedorRepository.findByCnpj(cnpj);
     if(forOpt.isPresent())
       return forOpt.get().convertFornecedorDTO();
    
     return null;
     
   }

   public FornecedorDTO buscarFornecedorRazaoSocial (String razaoSocial){
    Optional<Fornecedor> forOpt= fornecedorRepository.findByRazaoSocial(razaoSocial);
     if(forOpt.isPresent())
       return forOpt.get().convertFornecedorDTO();
    
     return null;
     
   }

   public List<FornecedorDTO> buscarFornecedorNomeFantasia (String nomeFantasia){
    Optional<List<Fornecedor>> forOpt = fornecedorRepository.findByNomeFantasiaLike("%"+nomeFantasia+"%");
     if(forOpt.isPresent()){
      return forOpt.get().stream().map(f -> f.convertFornecedorDTO()).collect(Collectors.toList());
     }
     return null;
   }

   public  List<FornecedorDTO> obterListFornecedor(){
    return fornecedorRepository.findAll().stream().map(f -> f.convertFornecedorDTO())
                            .collect(Collectors.toList());
  }
  
  public Page<FornecedorDTO> paginacaoFornecdor(Pageable paginacao){
    return fornecedorRepository.findAll(paginacao)
                               .map(Fornecedor::convertFornecedorDTO);
  }

   public FornecedorDTO deleteFornecedor(String cnpj){
    Optional<Fornecedor> forOp = fornecedorRepository.findByCnpj(cnpj);
    if(forOp.isPresent()){
      Fornecedor forn = forOp.get();
      fornecedorRepository.delete(forn);
      return forn.convertFornecedorDTO();
    }
    return null;
    
  }  

  public FornecedorDTO atualizarFornecedorPut(String cnpj, Fornecedor fornecedor){
    try {
        Optional<Fornecedor> cliOp = fornecedorRepository.findByCnpj(cnpj);
        if(!cliOp.isPresent()){
          return null;      
        }
        Fornecedor cli = cliOp.get();
        fornecedor.setId(cli.getId());
        return  saveFornecedor(fornecedor);
        
      } catch (Exception e) {
        return null;
      }
  }
}
