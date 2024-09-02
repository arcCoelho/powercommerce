package com.dstech.powercomerce.repositories;

import com.dstech.powercomerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
