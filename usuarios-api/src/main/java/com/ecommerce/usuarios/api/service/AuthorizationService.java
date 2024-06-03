package com.ecommerce.usuarios.api.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.repository.ClienteRepository;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
    Optional<Cliente> cliente =  clienteRepository.findByCpf(cpf);
    if( cliente.isPresent()){
      return cliente.get();
    }
     throw new UsernameNotFoundException("Cliente não encontrado!");
  }


  public BCryptPasswordEncoder getCryptPasswordEncoder(){
    return new  BCryptPasswordEncoder();
  }
  
}
