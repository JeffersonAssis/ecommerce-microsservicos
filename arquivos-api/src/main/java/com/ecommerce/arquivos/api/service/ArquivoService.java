package com.ecommerce.arquivos.api.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.arquivos.api.model.Arquivo;
import com.ecommerce.arquivos.api.repository.ArquivoRepository;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    public static final String ROOT_PATH = "../arquivos/";

    public boolean arquivoExist(String nome){
      Optional<Arquivo> optArq = arquivoRepository.findByNome(nome);
      return optArq.isPresent();
    }

    public Arquivo uploadFile(MultipartFile file) throws IOException{
      
      String pathString = ROOT_PATH + file.getOriginalFilename();
      Path path = Paths.get(pathString);
      Files.createDirectories(path.getParent());
      Files.write(path, file.getBytes());

      Arquivo arquivo = new Arquivo(); 
      arquivo.setNome(file.getOriginalFilename());
      arquivo.setDataHoraEnvio(LocalDateTime.now());
      arquivo.setTipo(file.getContentType());
      arquivo.setPath(pathString);
      
 
      return  arquivoRepository.save(arquivo);
     
    }

    public ByteArrayResource downloadArquivo(String nome) throws IOException{
      Optional<Arquivo> opArqui = arquivoRepository.findByNome(nome);

      if(opArqui.isPresent()){
        String path = opArqui.get().getPath();

        File file = new File(path);
        if(file.exists()){
          return  new ByteArrayResource(Files.readAllBytes(file.toPath()));
        }else{
          throw new FileNotFoundException("Diretorio não encontrado: " +path);
        }
        
      }else{
        throw new FileNotFoundException("Não do Arquivo está incorreto!");
      }

    }
}
