package com.ecommerce.produtos.api.repository;

import java.util.List;
import java.util.Optional;

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

  @Query("SELECT p FROM tb_produtos p where p.categoria.nome =:nomeCategoria")
  Optional<List<Produto>> findByCategoria(@Param("nomeCategoria") String nomeCategoria);

}
