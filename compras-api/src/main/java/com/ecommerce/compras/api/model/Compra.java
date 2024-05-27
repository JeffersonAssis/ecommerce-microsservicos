package com.ecommerce.compras.api.model;

import java.time.LocalDate;
import java.util.List;

import com.ecommerce.comrpas.client.compra.CompraDTO;
import com.ecommerce.comrpas.client.compra.ItemDTO;
import com.ecommerce.comrpas.client.usuario.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="tb_compras")
public class Compra {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email_cliente", nullable = false)
  private String emailCliente;

  @Column(nullable = false)
  private double total;

  @Column(name = "data", nullable = false, columnDefinition = "Date")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate data;

  @OneToMany
  @JoinTable(name = "tb_compras_itens", joinColumns = @JoinColumn(name = "id_compra"),
        inverseJoinColumns = @JoinColumn(name = "id_item"))
  private List<Item> itens;

  public CompraDTO converteCompra(ClienteDTO clienteDTO, List<ItemDTO> lDtos){
    CompraDTO cDto = new CompraDTO();
    cDto.setId(id);
    cDto.setClienteDTO(clienteDTO);
    cDto.setData(data);
    cDto.setTotal(total);
    cDto.setItensDto(lDtos);

    return cDto;
  }

}
