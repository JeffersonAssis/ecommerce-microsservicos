package com.ecommerce.produtos.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.produtos.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

  Optional<Categoria> findByNomeIgnoreCase(String nome);

}
