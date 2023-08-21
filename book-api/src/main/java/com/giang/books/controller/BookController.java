package com.giang.books.controller;


import com.giang.books.model.Book;
import com.giang.books.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;


    /*---Add new book---*/
    @PostMapping("/book")
    public ResponseEntity<?> save(@RequestBody Book book) {
        long id = bookService.save(book);
        return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
    }

    @GetMapping("/book/getAll")
    public ResponseEntity<?> getAllBooks() {
        List<Book> listBooks = new ArrayList<>(bookService.getAllBooks());
        if(listBooks.isEmpty())
            return ResponseEntity.ok().body("Empty");
        return ResponseEntity.ok().body(listBooks);
    }

    @GetMapping("/book/getAll/sort")
    public ResponseEntity<?> getAllBooksSort() {
        List<Book> listBooks = new ArrayList<>(bookService.getAllBooksSortedByDateCreated());
        if(listBooks.isEmpty())
            return ResponseEntity.ok().body("Empty");
        return ResponseEntity.ok().body(listBooks);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book bookFinded = bookService.getBookById(id);
        if(bookFinded == null)
            return ResponseEntity.ok("Empty Book");
        return ResponseEntity.ok().body(bookFinded);
    }

    @GetMapping("/book/getByCategory/{id}")
    public ResponseEntity<?> getBookByCategoryId(@PathVariable Long id) {
        List<Book> booksFinded = bookService.getAllBooksByCategory(id);
        if(booksFinded == null)
            return ResponseEntity.ok("Empty Book");
        return ResponseEntity.ok().body(booksFinded);
    }

}
