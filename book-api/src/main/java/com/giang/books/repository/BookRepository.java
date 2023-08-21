package com.giang.books.repository;

import com.giang.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.dateCreated DESC")
    List<Book> findAllOrderByDateCreatedDesc();

    List<Book> findByCategory_Id(Long id_category);
}
