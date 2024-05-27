package com.ecommerce.compras.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class Swagger {

  @Bean
	OpenAPI customApi(){
	     return new OpenAPI().info(new Info().title("Ecommerce-MicrosServiços Compras")
	     .version("1.0.0")
	     .license(new License().name("Lincença do Sistema").url("jbrunoassis@gmail.com")));
	       
	  }
}
