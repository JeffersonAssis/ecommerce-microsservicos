package com.ecommerce.produtos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.produtos.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
