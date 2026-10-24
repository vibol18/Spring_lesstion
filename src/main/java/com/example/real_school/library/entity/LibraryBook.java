package com.example.real_school.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "library_books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer totalCopies;
    private Integer availableCopies;
    private String rackNumber;
}
