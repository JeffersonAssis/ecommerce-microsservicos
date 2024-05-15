package com.ecommerce.compras.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.compras.api.model.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {

}
