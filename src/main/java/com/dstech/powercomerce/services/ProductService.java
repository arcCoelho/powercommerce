package com.dstech.powercomerce.services;

import com.dstech.powercomerce.dto.ProductDTO;
import com.dstech.powercomerce.entities.Product;
import com.dstech.powercomerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO findById(Long id){
        Optional<Product> result = repository.findById(id);
        Product produto = result.get();
        ProductDTO dto = new ProductDTO(produto);
        return dto;
    }

}
