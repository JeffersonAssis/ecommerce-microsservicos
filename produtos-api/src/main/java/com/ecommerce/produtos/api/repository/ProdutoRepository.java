package com.ecommerce.produtos.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.produtos.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

  Optional<List<Produto>> findByNomeLike(String nome);
 
  Optional<List<Produto>> findByPrecoLessThan(double preco);
 
  Optional<List<Produto>> findByPrecoGreaterThan(double preco);
 
  Optional<List<Produto>> findByPrecoBetween(double precoMin, double precoMax);
 
  Optional<Produto> findByCodigo(String codigo);

  Optional<Page<Produto>> findByNomeLike(String nome, Pageable pageable);
  
  Optional<Page<Produto>> findByPrecoGreaterThanEqual(Double preco, Pageable pageable);
  
  Optional<Page<Produto>> findByPrecoLessThanEqual(Double preco, Pageable pageable);
  
  Optional<Page<Produto>> findByCategoria(String categoria, Pageable pageable);

  @Query("SELECT p FROM tb_produtos p where p.categoria.nome =:nomeCategoria")
  Optional<List<Produto>> findByCategoria(@Param("nomeCategoria") String nomeCategoria);

  @Query("SELECT p.codigo FROM tb_produtos p ORDER BY p.id DESC LIMIT 1")
  Optional<String> findByProximoCod();

}
