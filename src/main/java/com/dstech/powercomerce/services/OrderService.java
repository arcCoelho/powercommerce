package com.dstech.powercomerce.services;

import com.dstech.powercomerce.dto.OrderDTO;
import com.dstech.powercomerce.entities.Order;
import com.dstech.powercomerce.repositories.OrderRepository;
import com.dstech.powercomerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findByid(Long id) {
        Order order = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Compra não encontrada"));
        return new OrderDTO(order);
    }
}
