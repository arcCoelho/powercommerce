package com.dstech.powercomerce.repositories;

import com.dstech.powercomerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT prod FROM Product prod WHERE UPPER(prod.name) LIKE  UPPER(CONCAT('%', :name, '%')) ")
    public Page<Product> searchByName(String name, Pageable pageable);

}
