package com.example.real_school.library.service;

import com.example.real_school.library.dto.BookDto;
import com.example.real_school.library.dto.LibraryTransactionDto;
import java.util.List;

public interface LibraryService {
    // Book Operations
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto createBook(BookDto dto);
    BookDto updateBook(Long id, BookDto dto);
    List<BookDto> searchBooks(String query);

    // Transaction Operations
    List<LibraryTransactionDto> getAllTransactions();
    LibraryTransactionDto issueBook(LibraryTransactionDto dto);
    LibraryTransactionDto returnBook(Long id);
    List<LibraryTransactionDto> getTransactionsByStudent(Long studentId);
    List<LibraryTransactionDto> getOverdueTransactions();
}
