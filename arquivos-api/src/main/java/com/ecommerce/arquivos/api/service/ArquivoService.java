package com.ecommerce.arquivos.api.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.arquivos.api.model.Arquivo;
import com.ecommerce.arquivos.api.repository.ArquivoRepository;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    public Arquivo uploadFile(MultipartFile file, String nome) throws IOException{
      String diretorio = "C:\\Users\\Davi\\AppData\\Local\\Temp\\tomcat.8084.8698719616056341416\\work\\Tomcat\\localhost\\ROOT\\src\\main\\resources\\assets";
      String path = diretorio+"\\"+file.getOriginalFilename();
     
      File dir = new File(diretorio);
     
      if(!dir.exists()){
        dir.mkdirs();
      }

      File fileNovo = new File(path);
      file.transferTo(fileNovo);

      Arquivo arquivo = new Arquivo();
 
      arquivo.setNome(nome);
 
      arquivo.setDataHoraEnvio(LocalDateTime.now());
      arquivo.setTipo(file.getContentType());
      arquivo.setPath(path);
      
 
      return  arquivoRepository.save(arquivo);
     
    }

    public byte[] downloadArquivo(String nome) throws IOException{
      Optional<Arquivo> opArqui = arquivoRepository.findByNome(nome);

      if(opArqui.isPresent()){
        String path = opArqui.get().getPath();

        File file = new File(path);
        if(file.exists()){
          return Files.readAllBytes(file.toPath());
        }else{
          throw new FileNotFoundException("Diretorio não encontrado: " +path);
        }
        
      }else{
        throw new FileNotFoundException("Não do Arquivo está incorreto!");
      }

    }
}
