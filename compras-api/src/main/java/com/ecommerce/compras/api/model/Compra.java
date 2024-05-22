package com.ecommerce.compras.api.model;

import java.time.LocalDate;
import java.util.List;

import com.ecommerce.comrpas.client.compra.CompraDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

  @NotNull(message = "O campo não pode ser nulo")
  @Email(message="Email inválido!")
  @Column(nullable = false)
  private String emailCliente;
  
  @NotNull(message = "O campo não pode ser nulo")
  @Column(nullable = false)
  private double total;

  @NotNull(message = "O campo não pode ser nulo")
  @Column(nullable = false)
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDate data;

  @OneToMany
  private List<Item> itens;

  public CompraDTO converteCompra(){
    CompraDTO cDto = new CompraDTO();

    return cDto;
  }

}
