package com.ecommerce.usuarios.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.usuarios.api.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {

  Optional<List<Fornecedor>> findByNomeFantasiaLike(String nome);
  Optional<Fornecedor> findByCnpj(String cnpj);
  Optional<Fornecedor> findByRazaoSocial(String razaoSocial);
  Optional<Fornecedor> findByEmail(String email);

}
