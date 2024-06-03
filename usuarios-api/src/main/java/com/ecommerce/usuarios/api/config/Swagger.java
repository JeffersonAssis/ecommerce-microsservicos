package com.ecommerce.usuarios.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class Swagger {

 
	@Bean
	public OpenAPI customApi() {
			return new OpenAPI()
							.info(new Info()
											.title("Ecommerce-MicrosServiços Clientes")
											.version("1.0.0")
											.license(new License()
															.name("Licença do Sistema")
															.url("mailto:jbrunoassis@gmail.com")));
	}

		
}
