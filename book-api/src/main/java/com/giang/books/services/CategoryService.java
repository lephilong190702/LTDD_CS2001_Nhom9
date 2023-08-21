package com.giang.books.services;

import com.giang.books.model.Category;
import com.giang.books.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAll(){
        return categoryRepository.findAll().stream().toList();
    }
}
