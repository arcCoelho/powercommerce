package com.dstech.powercomerce.dto.pedidos;

import com.dstech.powercomerce.entities.User;

public class ClientDTO {

    private Long id;
    private String nome;

    public ClientDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ClientDTO(User entity) {
        this.id = entity.getId();
        this.nome = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
