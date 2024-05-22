package com.ecommerce.produtos.api.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.comrpas.client.produto.CategoriaDTO;
import com.ecommerce.produtos.api.model.Categoria;
import com.ecommerce.produtos.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
  
  private final CategoriaRepository categoriaRepository;

  @Autowired
  public CategoriaService(CategoriaRepository categoriaRepository){
    this.categoriaRepository = categoriaRepository;
  }
  
  public CategoriaDTO categoriaSave(Categoria categoria){
    CategoriaDTO cDto = categoriaRepository.save(categoria).convertCategoriaDTO();
    if(Objects.nonNull(cDto))
      return cDto;

    return null;
  }

  public CategoriaDTO categoiraDelete(String nome){
    Optional<Categoria> cOpt = categoriaRepository.findByNomeIgnoreCase(nome);
    if(cOpt.isPresent()){
    Categoria c = cOpt.get();
    categoriaRepository.delete(c);
    return c.convertCategoriaDTO();
    }
    return null;
  }

  public List<CategoriaDTO> listarCategorias(){
    return categoriaRepository.findAll().stream().map(c -> c.convertCategoriaDTO()).collect(Collectors.toList());
  }

}
