package com.ecommerce.compras.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.compras.api.model.Compra;
import com.ecommerce.compras.api.model.Item;
import com.ecommerce.compras.api.repository.CompraRepository;
import com.ecommerce.comrpas.client.compra.CompraDTO;
import com.ecommerce.comrpas.client.compra.ItemDTO;
import com.ecommerce.comrpas.client.usuario.ClienteDTO;

@Service
public class CompraService {
   
  private final CompraRepository compraRepository;
  private final ItemService itemService;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  public CompraService(CompraRepository compraRepository, ItemService itemService){
  this.compraRepository = compraRepository; 
  this.itemService = itemService; 
  }

  public CompraDTO saveCompra(Compra compra){
    compra.setData(LocalDate.now());
    List<Item> listItem = new ArrayList<>();
    listItem.addAll(compra.getItens());
    listItem.forEach(i -> {
     Item item = itemService.save(i);
     i.setPreco(item.getPreco());
    });
     compra.setTotal(listItem.stream().mapToDouble( i ->{
      Item item = itemService.findById(i.getId());
      return item.getPreco() * item.getQuantidade();
    }).sum() );

    ClienteDTO clienteDTO = clienteService.buscarCliente(compra.getEmailCliente());
    List<ItemDTO> lDtos = itemService.convertLisDto(listItem);
    return compraRepository.save(compra).converteCompra(clienteDTO, lDtos);
   
  }

  public List<CompraDTO> obterTodasCompras(){
    List<Compra> lCompras = compraRepository.findAll();

  return lCompras.stream().map(c -> {
      ClienteDTO cDto = clienteService.buscarCliente(c.getEmailCliente());
      List<ItemDTO> iDtos  = itemService.convertLisDto(c.getItens()); 
     return c.converteCompra(cDto, iDtos);
    }).collect(Collectors.toList());

   
  }

  public List<CompraDTO> obterTodasComprasEmail(String email) {

  Optional<List<Compra>> olCompras = compraRepository.findByEmailCliente(email);
  if(olCompras.isPresent()){
    List<Compra> lCompras = olCompras.get();
  
    return lCompras.stream().map(c -> {
      ClienteDTO cDto = clienteService.buscarCliente(c.getEmailCliente());
      List<ItemDTO> iDtos  = itemService.convertLisDto(c.getItens()); 
     return c.converteCompra(cDto, iDtos);
    }).collect(Collectors.toList());
  }else{
    return Collections.emptyList();
  }
 
  }

 

  public List<CompraDTO> obterTodasComprasData(LocalDate inicio, LocalDate fim) {
    Optional<List<Compra>> olCompras = compraRepository.findByDataBetween(inicio, fim);
  if(olCompras.isPresent()){
    List<Compra> lCompras = olCompras.get();
  
    return lCompras.stream().map(c -> {
      ClienteDTO cDto = clienteService.buscarCliente(c.getEmailCliente());
      List<ItemDTO> iDtos  = itemService.convertLisDto(c.getItens()); 
     return c.converteCompra(cDto, iDtos);
    }).collect(Collectors.toList());
  }else{
    return Collections.emptyList();
  }
  }

}
