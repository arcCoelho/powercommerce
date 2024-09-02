package com.dstech.powercomerce.repositories;

import com.dstech.powercomerce.entities.OrderItem;
import com.dstech.powercomerce.entities.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRespository extends JpaRepository<OrderItem, OrderItemPk> {
}
