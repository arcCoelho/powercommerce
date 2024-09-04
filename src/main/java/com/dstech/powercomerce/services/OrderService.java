package com.dstech.powercomerce.services;

import com.dstech.powercomerce.dto.OrderDTO;
import com.dstech.powercomerce.dto.pedidos.OrderItemDTO;
import com.dstech.powercomerce.entities.*;
import com.dstech.powercomerce.repositories.OrderItemRespository;
import com.dstech.powercomerce.repositories.OrderRepository;
import com.dstech.powercomerce.repositories.ProductRepository;
import com.dstech.powercomerce.services.exceptions.ForbiddenException;
import com.dstech.powercomerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRespository orderItemRespository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findByid(Long id) {
        Order order = orderRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Compra não encontrada"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO save(OrderDTO dto) {
        Order orderEntity = new Order();
        orderEntity.setMoment(ZonedDateTime.now( ZoneId.of( "America/Sao_Paulo")  ).toInstant());
        orderEntity.setStatus(OrderStatus.WAINTING_PAYMENT);

        User userLogged = userService.authenticated();
        orderEntity.setClient(userLogged);

        for(OrderItemDTO item : dto.getItems()){
            Product product = productRepository.getReferenceById(item.getProductId());
            OrderItem orderItem = new OrderItem(orderEntity, product, item.getQuantity(), product.getPrice());
            orderEntity.addItem( orderItem );
        }

        orderRepository.save(orderEntity);
        orderItemRespository.saveAll(orderEntity.getItems());

        return new OrderDTO(orderEntity);
    }
}
