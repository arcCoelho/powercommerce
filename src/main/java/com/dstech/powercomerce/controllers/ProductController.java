package com.dstech.powercomerce.controllers;

import com.dstech.powercomerce.dto.ProductDTO;
import com.dstech.powercomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return  ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> listAll(Pageable pageable){
        Page page =  service.findAll(pageable);
        return ResponseEntity.ok(page);
    }
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product){
        ProductDTO dto = service.insert(product);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(dto.getId())
                    .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO product){
        ProductDTO dto = service.update(id, product);

        return  ResponseEntity.accepted().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
