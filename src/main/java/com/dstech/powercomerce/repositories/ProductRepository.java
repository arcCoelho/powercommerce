package com.dstech.powercomerce.repositories;

import com.dstech.powercomerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
