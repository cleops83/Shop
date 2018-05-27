package com.shop.services;


import com.shop.entities.Category;
import com.shop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getAll(){
        return categoryRepository.findAll();

    }
}
