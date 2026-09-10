package com.example.real_school.library.controller;

import com.example.real_school.library.dto.BookDto;
import com.example.real_school.library.dto.LibraryTransactionDto;
import com.example.real_school.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    // Books
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.createBook(dto));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.getBookById(id));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto dto) {
        return ResponseEntity.ok(libraryService.updateBook(id, dto));
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<BookDto>> searchBooks(@RequestParam String q) {
        return ResponseEntity.ok(libraryService.searchBooks(q));
    }

    // Transactions
    @GetMapping("/transactions")
    public ResponseEntity<List<LibraryTransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(libraryService.getAllTransactions());
    }

    @PostMapping("/transactions/issue")
    public ResponseEntity<LibraryTransactionDto> issueBook(@RequestBody LibraryTransactionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.issueBook(dto));
    }

    @PostMapping("/transactions/{id}/return")
    public ResponseEntity<LibraryTransactionDto> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.returnBook(id));
    }

    @GetMapping("/transactions/student/{studentId}")
    public ResponseEntity<List<LibraryTransactionDto>> getTransactionsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(libraryService.getTransactionsByStudent(studentId));
    }

    @GetMapping("/transactions/overdue")
    public ResponseEntity<List<LibraryTransactionDto>> getOverdueTransactions() {
        return ResponseEntity.ok(libraryService.getOverdueTransactions());
    }
}
