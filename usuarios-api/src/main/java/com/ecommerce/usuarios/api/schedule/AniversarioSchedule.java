package com.ecommerce.usuarios.api.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.repository.ClienteRepository;

@Component
@EnableScheduling
public class AniversarioSchedule{

  @Scheduled(cron = CRON_EXPRESSION, zone = TIME_ZONE)
  public void enviarNotificacao(){
    List<Cliente> clientes = clienteRepository.findAll();

    for(Cliente cliente : clientes){
      int dia = cliente.getDataNascimento().getDayOfMonth();
      int mes = cliente.getDataNascimento().getMonthValue();

      String data = String.format("%02d/%02d", dia, mes);

      int diaAtual = LocalDate.now().getDayOfMonth();
      int mesAtual = LocalDate.now().getMonthValue();

      String dataAtual = String.format("%02d/%02d", diaAtual, mesAtual);

      if(data.equals(dataAtual)){
        gerarEmail(cliente);
      }
    }
  }

  private void gerarEmail(Cliente cliente){

      SimpleMailMessage email = new SimpleMailMessage();

      email.setFrom(emailString);
      email.setTo(cliente.getEmail());
      email.setSubject("Hoje é seu dia!!!");
      email.setText("Feliz Aniversairio");
      
      javaMailSender.send(email);

  }

  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private JavaMailSender javaMailSender;

  private static final String TIME_ZONE = "America/Sao_Paulo";
  private static final String CRON_EXPRESSION =  "0 55 20 * * *";

  @Value("spring.mail.sender")
  private String emailString;
}