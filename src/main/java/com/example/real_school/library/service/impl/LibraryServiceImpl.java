package com.example.real_school.library.service.impl;

import com.example.real_school.library.dto.BookDto;
import com.example.real_school.library.dto.LibraryTransactionDto;
import com.example.real_school.library.entity.LibraryBook;
import com.example.real_school.library.entity.LibraryTransaction;
import com.example.real_school.library.repository.LibraryBookRepository;
import com.example.real_school.library.repository.LibraryTransactionRepository;
import com.example.real_school.library.service.LibraryService;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    private final LibraryBookRepository bookRepository;
    private final LibraryTransactionRepository transactionRepository;
    private final StudentRepository studentRepository;

    public LibraryServiceImpl(LibraryBookRepository bookRepository,
                              LibraryTransactionRepository transactionRepository,
                              StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.transactionRepository = transactionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToBookDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        LibraryBook book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return mapToBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto dto) {
        LibraryBook book = new LibraryBook();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublisher(dto.getPublisher());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getTotalCopies()); // all available initially
        book.setRackNumber(dto.getRackNumber());
        return mapToBookDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateBook(Long id, BookDto dto) {
        LibraryBook book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublisher(dto.getPublisher());
        
        int diff = dto.getTotalCopies() - book.getTotalCopies();
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(book.getAvailableCopies() + diff);
        book.setRackNumber(dto.getRackNumber());
        
        return mapToBookDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> searchBooks(String query) {
        // Simple search for title or author
        return bookRepository.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(query.toLowerCase()) || 
                             b.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryTransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::mapToTransactionDto)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryTransactionDto issueBook(LibraryTransactionDto dto) {
        LibraryBook book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
                
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LibraryTransaction tx = new LibraryTransaction();
        tx.setLibraryBook(book);
        tx.setStudent(student);
        tx.setIssueDate(LocalDate.now());
        tx.setDueDate(LocalDate.now().plusDays(14)); // 14 days default
        tx.setStatus("ISSUED");

        return mapToTransactionDto(transactionRepository.save(tx));
    }

    @Override
    public LibraryTransactionDto returnBook(Long id) {
        LibraryTransaction tx = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
                
        if (!"ISSUED".equals(tx.getStatus()) && !"OVERDUE".equals(tx.getStatus())) {
            throw new RuntimeException("Book already returned");
        }
        
        tx.setReturnDate(LocalDate.now());
        tx.setStatus("RETURNED");
        
        if (tx.getReturnDate().isAfter(tx.getDueDate())) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(tx.getDueDate(), tx.getReturnDate());
            tx.setFineAmount(daysLate * 5.0); // 5 per day fine example
        } else {
            tx.setFineAmount(0.0);
        }

        LibraryBook book = tx.getLibraryBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return mapToTransactionDto(transactionRepository.save(tx));
    }

    @Override
    public List<LibraryTransactionDto> getTransactionsByStudent(Long studentId) {
        return transactionRepository.findAll().stream()
                .filter(t -> t.getStudent() != null && t.getStudent().getId().equals(studentId))
                .map(this::mapToTransactionDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryTransactionDto> getOverdueTransactions() {
        LocalDate today = LocalDate.now();
        return transactionRepository.findAll().stream()
                .filter(t -> !"RETURNED".equals(t.getStatus()) && t.getDueDate().isBefore(today))
                .map(this::mapToTransactionDto)
                .collect(Collectors.toList());
    }

    private BookDto mapToBookDto(LibraryBook book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPublisher(book.getPublisher());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setAvailableCopies(book.getAvailableCopies());
        dto.setRackNumber(book.getRackNumber());
        return dto;
    }

    private LibraryTransactionDto mapToTransactionDto(LibraryTransaction tx) {
        LibraryTransactionDto dto = new LibraryTransactionDto();
        dto.setId(tx.getId());
        dto.setBookId(tx.getLibraryBook().getId());
        dto.setStudentId(tx.getStudent().getId());
        dto.setIssueDate(tx.getIssueDate());
        dto.setDueDate(tx.getDueDate());
        dto.setReturnDate(tx.getReturnDate());
        dto.setFineAmount(tx.getFineAmount());
        dto.setStatus(tx.getStatus());
        return dto;
    }
}
