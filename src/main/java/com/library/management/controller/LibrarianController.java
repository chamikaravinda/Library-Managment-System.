package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.model.BookCopy;
import com.library.management.model.Borrower;
import com.library.management.service.LibrarianService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/librarian")
@Tag(name="Librarian")
public class LibrarianController {

    private LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(librarianService.addBook(book));
    }

    @PostMapping("/books/{isbn}/copies")
    public ResponseEntity<BookCopy> addBookCopy(@PathVariable Long isbn) {
        return ResponseEntity.ok(librarianService.addBookCopy(isbn));
    }

    @PostMapping("/borrowers")
    public ResponseEntity<Borrower> registerBorrower(@RequestBody @Valid Borrower borrower) {
        return ResponseEntity.ok(librarianService.registerBorrower(borrower));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(librarianService.getAvailableBooks());
    }
}