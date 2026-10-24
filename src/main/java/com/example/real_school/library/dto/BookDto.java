package com.example.real_school.library.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer totalCopies;
    private Integer availableCopies;
    private String rackNumber;
}
