package com.dstech.powercomerce.entities;

import jakarta.persistence.*;

import java.time.Instant;
@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus status;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {
    }

    public Order(Long id, OrderStatus status, Instant moment, User client) {
        this.id = id;
        this.status = status;
        this.moment = moment;
        this.client = client;
    }

    public Order(Long id, OrderStatus status, Instant moment, User client, Payment payment) {
        this.id = id;
        this.status = status;
        this.moment = moment;
        this.client = client;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
