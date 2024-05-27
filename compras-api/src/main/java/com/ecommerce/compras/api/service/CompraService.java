package com.ecommerce.compras.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

}
