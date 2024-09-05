package com.dstech.powercomerce.services;

import com.dstech.powercomerce.dto.CategoryDTO;
import com.dstech.powercomerce.entities.Category;
import com.dstech.powercomerce.repositories.CateggoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CategoryService {

    @Autowired
    private CateggoryRepository categgoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> listAll() {
        List<Category> categories = categgoryRepository.findAll();
        return categories.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
