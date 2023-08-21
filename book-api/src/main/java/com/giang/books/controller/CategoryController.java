package com.giang.books.controller;


import com.giang.books.model.Book;
import com.giang.books.model.Category;
import com.giang.books.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/getAll")
    public ResponseEntity<?> getAllCategorys() {
        List<Category> listCategorys = new ArrayList<>(categoryService.getAll());
        if(listCategorys.isEmpty())
            return ResponseEntity.ok().body("Empty");
        return ResponseEntity.ok().body(listCategorys);
    }
}
