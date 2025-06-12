package com.library.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ISBN cannot be null")
    @Column(unique = true)
    private Long isbn;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;
}
