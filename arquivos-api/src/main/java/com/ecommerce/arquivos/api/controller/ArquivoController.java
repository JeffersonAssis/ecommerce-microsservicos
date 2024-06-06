package com.ecommerce.arquivos.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
  public ResponseEntity<Arquivo> uploadArquivo(@RequestParam("arquivo") MultipartFile arquivo) throws IOException{
    
     String contentType = arquivo.getContentType();
    if(!contentType.equals("application/pdf")){
      return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
    }
    if(arquivoService.arquivoExist(arquivo.getOriginalFilename())){
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
    }
  
    return ResponseEntity.ok().body(arquivoService.uploadFile(arquivo));
  }

  @GetMapping
  public ResponseEntity<ByteArrayResource> downloadArquivo(@RequestParam("nome") String nome) throws IOException{
    if(!arquivoService.arquivoExist(nome)){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Disposition", "attachment; filename="+nome);

      ByteArrayResource arquivo = arquivoService.downloadArquivo(nome);
    return ResponseEntity.ok()
                          .headers(headers)
                          .contentLength(arquivo.contentLength())
                          .contentType(MediaType.APPLICATION_PDF)
                          .body(arquivo);
        }

}
