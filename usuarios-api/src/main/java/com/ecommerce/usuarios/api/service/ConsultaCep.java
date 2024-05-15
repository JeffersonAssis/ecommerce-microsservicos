package com.ecommerce.usuarios.api.service;

import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ecommerce.usuarios.api.model.Endereco;


@FeignClient(name="viacep", url ="https://viacep.com.br/ws")
public interface ConsultaCep {

    @GetMapping("/{cep}/json")
    Optional<Endereco> viaCep(@PathVariable String cep);

}
