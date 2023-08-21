package com.giang.books.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", initialValue = 3000)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Date dateCreated;
}
