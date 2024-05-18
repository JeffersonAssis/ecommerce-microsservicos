package com.ecommerce.produtos.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.produtos.api.dto.ProdutoDTO;
import com.ecommerce.produtos.api.model.Produto;
import com.ecommerce.produtos.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
  
  private final ProdutoRepository produtoRepository;

  @Autowired
  public ProdutoService(ProdutoRepository produtoRepository){
    this.produtoRepository = produtoRepository;
  }


  public ProdutoDTO produtoSave(Produto produto){
    ProdutoDTO pDto = produtoRepository.save(produto).conveProdutoDTO();
    if(Objects.nonNull(pDto))
      return pDto;

    return null;
  }

  public List<ProdutoDTO> produtoSaveMuitos(List<Produto> produto){
    List<ProdutoDTO> pDto = produtoRepository.saveAll(produto).stream().map(p -> p.conveProdutoDTO()).collect(Collectors.toList());
    if(Objects.nonNull(pDto))
      return pDto;

    return Collections.emptyList();
  }

  public ProdutoDTO produtoDelete(String codigo){
    Optional<Produto> pOpt = produtoRepository.findByCodigo(codigo);
    if(pOpt.isPresent()){
    Produto c = pOpt.get();
    produtoRepository.delete(c);
    return c.conveProdutoDTO();
    }
    return null;
  }

  public List<ProdutoDTO> listarProdutoDTOs(){
    return produtoRepository.findAll().stream().map(p -> p.conveProdutoDTO()).collect(Collectors.toList());
  }

  public List<ProdutoDTO> findByNomeLike(String nome){
    Optional<List<Produto>> listProOp = produtoRepository.findByNomeLike("%"+nome+"%");
    if(listProOp.isPresent()){
      List<Produto> listProduto = listProOp.get();
      return listProduto.stream().map(p -> p.conveProdutoDTO()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }
  
  public List<ProdutoDTO> findByMenorPreco(double menorPreco){
    Optional<List<Produto>> listProOp = produtoRepository.findByPrecoLessThan(menorPreco);
    if(listProOp.isPresent()){
      List<Produto> listProduto = listProOp.get();
      return listProduto.stream().map(p -> p.conveProdutoDTO()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public List<ProdutoDTO> findByMaiorPreco(double maiorPreco){
    Optional<List<Produto>> listProOp = produtoRepository.findByPrecoGreaterThan(maiorPreco);
    if(listProOp.isPresent()){
      List<Produto> listProduto = listProOp.get();
      return listProduto.stream().map(p -> p.conveProdutoDTO()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public List<ProdutoDTO> findByDiferencaPreco(double menorPreco, double maiorPreco ){
    Optional<List<Produto>> listProOp = produtoRepository.findByPrecoBetween(menorPreco, maiorPreco);
    if(listProOp.isPresent()){
      List<Produto> listProduto = listProOp.get();
      return listProduto.stream().map(p -> p.conveProdutoDTO()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }
}
