package com.giang.books.services;


import com.giang.books.model.Author;
import com.giang.books.model.Book;
import com.giang.books.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    public List<Author> getAllAuthors(){
        return authorRepository.findAll().stream().toList();
    }

    public List<Author> getAllAuthorsSortedByDateCreated() {
        return authorRepository.findAllOrderByDateCreatedDesc();
    }
}
