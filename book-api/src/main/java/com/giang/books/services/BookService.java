package com.giang.books.services;


import com.giang.books.model.Book;
import com.giang.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public long save(Book book) {
        return bookRepository.save(book).getId();
    }

    public List<Book> getAllBooks(){
        return  bookRepository.findAll().stream().toList();
    }

    public List<Book> getAllBooksSortedByDateCreated() {
        return bookRepository.findAllOrderByDateCreatedDesc();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> getAllBooksByCategory(Long id_category){
        return bookRepository.findByCategory_Id(id_category).stream().toList();
    }
}
