package com.giang.books.repository;

import com.giang.books.model.Author;
import com.giang.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.dateCreated DESC")
    List<Author> findAllOrderByDateCreatedDesc();
}
