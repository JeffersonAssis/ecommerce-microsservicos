package com.ecommerce.produtos.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.produtos.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

  Optional<List<Produto>> findByNomeLika(String nome);
 
  Optional<List<Produto>> findByPrecoSmallerThan(double preco);
 
  Optional<List<Produto>> findByPrecoGreaterThan(double preco);
 
  Optional<List<Produto>> findByPrecoBetween(double precoMin, double precoMax);
 
  Optional<Produto> findByCodigo(String codigo);

  @Query("SELECT p FROM Produto p JOIN p.categoria c where c.nome = :nome")
  Optional<List<Produto>> findByCategoria(String nome);

}
