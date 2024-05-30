package com.ecommerce.usuarios.api.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.usuarios.api.model.Cliente;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  private ClienteService clienteService;

  @Override
  public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
    Cliente cliente =  clienteService.buscarClienteCpfA(cpf);
    if(Objects.nonNull(cliente)){
      return cliente;
    }
     throw new UsernameNotFoundException("Cliente não encontrado!");
  }

  @Bean
  public BCryptPasswordEncoder getCryptPasswordEncoder(){
    return new  BCryptPasswordEncoder();
  }
  
}
