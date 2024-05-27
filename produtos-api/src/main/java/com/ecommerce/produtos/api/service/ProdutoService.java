package com.ecommerce.produtos.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.comrpas.client.produto.ProdutoDTO;
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
   Optional<String> cod = produtoRepository.findByProximoCod();
   int codigo = Integer.parseInt(cod.get())+1;
   produto.setCodigo(String.valueOf(codigo));
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
  
  public ProdutoDTO findByCodigo(String codigo){
    Optional<Produto> listProOp = produtoRepository.findByCodigo(codigo);
    if(listProOp.isPresent()){
      return listProOp.get().conveProdutoDTO();
    }
    return new ProdutoDTO();
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

  public List<ProdutoDTO> findByCategoria(String catProd){
    Optional<List<Produto>> listProOp = produtoRepository.findByCategoria(catProd);
    if(listProOp.isPresent()){
      List<Produto> listProduto = listProOp.get();
      return listProduto.stream().map(p -> p.conveProdutoDTO()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public Page<ProdutoDTO> findByCategoria(String catProd, Pageable pageable){
    Optional<Page<Produto>> listProOp = produtoRepository.findByCategoria(catProd, pageable);
    if(listProOp.isPresent()){
      Page<Produto> listProduto = listProOp.get();
      return listProduto.map(Produto::conveProdutoDTO);
    }
    return null;
  }

  public Page<ProdutoDTO> findByMenorPreco(double menorPreco, Pageable pageable){
    Optional<Page<Produto>> listProOp = produtoRepository.findByPrecoLessThanEqual(menorPreco,pageable);
    if(listProOp.isPresent()){
      Page<Produto> listProduto = listProOp.get();
      return listProduto.map(Produto::conveProdutoDTO);
    }
    return null;
  }

  public Page<ProdutoDTO> findByMaiorPreco(double maiorPreco, Pageable pageable){
    Optional<Page<Produto>> listProOp = produtoRepository.findByPrecoGreaterThanEqual(maiorPreco,pageable);
    if(listProOp.isPresent()){
     Page<Produto> listProduto = listProOp.get();
      return listProduto.map(Produto::conveProdutoDTO);
    }
    return null;
  }


  public Page<ProdutoDTO> findByNomeLike(String nome, Pageable pageable){
    Optional<Page<Produto>> listProOp = produtoRepository.findByNomeLike("%"+nome+"%",pageable);
    if(listProOp.isPresent()){
      Page<Produto> listProduto = listProOp.get();
      return listProduto.map(Produto::conveProdutoDTO);
    }
    return  null;
  }
}
