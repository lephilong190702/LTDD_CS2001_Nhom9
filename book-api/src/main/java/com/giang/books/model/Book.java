package com.giang.books.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "author_id")
    private Author author;
    @NotNull
    private Double price;
    @NotNull
    private String img;
    @NotNull
    private String description;
    @NotNull
    private Date dateCreated;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "discount_id")
    private Discount discount;

    public Book() {}
}
