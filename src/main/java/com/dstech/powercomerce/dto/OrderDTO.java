package com.dstech.powercomerce.dto;

import com.dstech.powercomerce.dto.pedidos.ClientDTO;
import com.dstech.powercomerce.dto.pedidos.OrderItemDTO;
import com.dstech.powercomerce.dto.pedidos.PaymentDTO;
import com.dstech.powercomerce.entities.Order;
import com.dstech.powercomerce.entities.OrderItem;
import com.dstech.powercomerce.entities.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.client = new ClientDTO(entity.getClient());
        this.payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment()) ;

        this.items.clear();
        for (OrderItem item : entity.getItems()){
            this.items.add( new OrderItemDTO(item) );
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public Double getTotal(){
        Double total = 0.0;
        for (OrderItemDTO dto : this.getItems()){
            total = total + dto.getSubTotal();
        }
        return total;
    }
}
