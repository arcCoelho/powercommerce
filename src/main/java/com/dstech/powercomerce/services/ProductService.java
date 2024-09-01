package com.dstech.powercomerce.services;

import com.dstech.powercomerce.dto.ProductDTO;
import com.dstech.powercomerce.entities.Product;
import com.dstech.powercomerce.repositories.ProductRepository;
import com.dstech.powercomerce.services.exceptions.DatabaseException;
import com.dstech.powercomerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO findById(Long id){
        Product result = repository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Produto com id: "+id+" nao encontrado") );
        ProductDTO dto = new ProductDTO(result);
        return dto;
    }
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable){
        Page<Product> result = repository.searchByName(name, pageable);
        return result.map(x->new ProductDTO(x));
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Produto com id: "+id+" nao encontrado");
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade relacional");
        }
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Product entity = modelMapper.map(dto, Product.class);
        entity = repository.save(entity);
        ProductDTO result = modelMapper.map(entity, ProductDTO.class);
        return result;
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setImgUrl(dto.getImgUrl());

            entity = repository.save(entity);

            ModelMapper modelMapper = new ModelMapper();
            ProductDTO result = modelMapper.map(entity, ProductDTO.class);
            return result;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Produto com id: "+id+" nao encontrado");
        }

    }
}
