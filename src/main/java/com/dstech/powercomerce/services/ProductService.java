package com.dstech.powercomerce.services;

import com.dstech.powercomerce.dto.ProductDTO;
import com.dstech.powercomerce.entities.Product;
import com.dstech.powercomerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(x->new ProductDTO(x));
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Product entity = modelMapper.map(dto, Product.class);
        entity = repository.save(entity);
        ProductDTO result = modelMapper.map(entity, ProductDTO.class);
        return result;
    }
}
