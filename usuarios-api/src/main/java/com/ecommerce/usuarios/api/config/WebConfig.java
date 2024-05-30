package com.ecommerce.usuarios.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	 	@Override
	  public void addCorsMappings(@NonNull CorsRegistry registry) {
	        registry.addMapping("/**")//as rotas da aplicação /clientes/bucasr** Seria especifico
	            .allowedOrigins("**")//rota para liberar o acesso a api. /http:/localhost:8082
	            .allowedMethods("GET", "POST", "PUT", "DELETE")
	            .allowCredentials(true)
              .maxAge(3600);
	  }
  
}
