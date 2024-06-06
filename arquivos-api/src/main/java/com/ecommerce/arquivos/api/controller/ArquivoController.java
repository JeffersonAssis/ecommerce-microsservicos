package com.ecommerce.arquivos.api.controller;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.arquivos.api.model.Arquivo;
import com.ecommerce.arquivos.api.service.ArquivoService;

@RestController
@RequestMapping(value = "/arquivos")
public class ArquivoController {

  @Autowired
  private ArquivoService arquivoService;

 
  @PostMapping(value = "/upload")
  public ResponseEntity<Arquivo> uploadArquivo(@RequestParam("arquivo") MultipartFile arquivo, @RequestParam("nome")String nome) throws IOException{
    Arquivo arq  = arquivoService.uploadFile(arquivo, nome);
    if(Objects.nonNull(arq))
    return new ResponseEntity<>(arq, HttpStatus.CREATED);

    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping
  public ResponseEntity<?> downloadArquivo(@RequestParam("nome") String nome) throws IOException{
    return ResponseEntity.ok().body(arquivoService.downloadArquivo(nome));
  }

}
