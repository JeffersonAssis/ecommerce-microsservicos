package com.ecommerce.usuarios.api.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.usuarios.api.service.AuthorizationService;
import com.ecommerce.usuarios.api.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

  @Autowired
  private TokenService tokenService;

  @Autowired 
  private AuthorizationService authorizationService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
      throws ServletException, IOException {
        String Token = _getTokeString(request);

        if(Objects.nonNull(Token)){
          String subject = tokenService.getSubject(Token);

          UserDetails cliente = authorizationService.loadUserByUsername(subject);

          Authentication authentication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
          
          SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
  }

  private String _getTokeString(HttpServletRequest request){
    String autorizaString = request.getHeader("Authorization");
    if(Objects.nonNull(autorizaString)){
      return autorizaString.replace("Bearer ","");
    }
    return null; 
  }

}
