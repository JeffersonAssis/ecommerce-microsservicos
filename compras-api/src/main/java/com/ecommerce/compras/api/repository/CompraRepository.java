package com.ecommerce.compras.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.compras.api.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

 Optional<List<Compra>> findByEmailCliente(String email);

 Optional<List<Compra>> findByDataBetween(LocalDate inicio, LocalDate fim);

}
