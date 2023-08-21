package com.giang.books.controller;


import com.giang.books.model.Author;
import com.giang.books.model.Book;
import com.giang.books.services.AuthorService;
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
public class AuthorController {


    @Autowired
    private AuthorService authorService;

    @GetMapping("/author/getAll/sort")
    public ResponseEntity<?> getAllAuthorSort() {
        List<Author> listAuthors = new ArrayList<>(authorService.getAllAuthorsSortedByDateCreated());
        if(listAuthors.isEmpty())
            return ResponseEntity.ok().body("Empty");
        return ResponseEntity.ok().body(listAuthors);
    }
}
