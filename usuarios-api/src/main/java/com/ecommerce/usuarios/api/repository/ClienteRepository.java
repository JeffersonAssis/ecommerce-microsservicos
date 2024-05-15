package com.ecommerce.usuarios.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.usuarios.api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

 Optional<Cliente> findByEmail(String email);

 Optional<Cliente> findByCpf(String cpf);

 //Optional<Cliente> findByNomeAndCpf(String nome, String cpf);

 Optional<List<Cliente>> findByNomeLike(String nome);

 //Optional<List<Cliente>> findByNome(String nome);

 Optional<List<Cliente>> findByNomeContainsIgnoreCase(String nome);

 Optional<List<Cliente>> findByDataNascimentoBetween(LocalDate inicio, LocalDate fim);

 boolean existsByEmail(String email);

}
