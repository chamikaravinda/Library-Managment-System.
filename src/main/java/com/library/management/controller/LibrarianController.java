package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.model.BookCopy;
import com.library.management.model.Borrower;
import com.library.management.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(librarianService.addBook(book));
    }

    @PostMapping("/books/{isbn}/copies")
    public ResponseEntity<BookCopy> addBookCopy(@PathVariable String isbn) {
        return ResponseEntity.ok(librarianService.addBookCopy(isbn));
    }

    @PostMapping("/borrowers")
    public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower) {
        return ResponseEntity.ok(librarianService.registerBorrower(borrower));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(librarianService.getAvailableBooks());
    }
}