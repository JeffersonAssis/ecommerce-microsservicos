package com.ecommerce.arquivos.api.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_arquivo")
public class Arquivo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable= false, name = "nome", unique =true)
  private String nome;

  @Column(nullable = false)
  private String tipo;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(nullable = false, name ="data_hora_envio")
  private LocalDateTime dataHoraEnvio;

  @Column(nullable = false)
  private String path;
} 
