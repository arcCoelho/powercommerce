package com.dstech.powercomerce.controllers;

import com.dstech.powercomerce.dto.CategoryDTO;
import com.dstech.powercomerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listAll(){
        List<CategoryDTO> lista = categoryService.listAll();
        return ResponseEntity.ok(lista);
    }


}
