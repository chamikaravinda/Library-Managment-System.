package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.model.BookCopy;
import com.library.management.model.Borrower;
import com.library.management.service.LibrarianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibrarianControllerTest {

    @Mock
    private LibrarianService librarianService;

    @InjectMocks
    private LibrarianController librarianController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook_ShouldReturnBook() {
        Book book = new Book();
        when(librarianService.addBook(book)).thenReturn(book);

        ResponseEntity<Book> response = librarianController.addBook(book);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
        verify(librarianService, times(1)).addBook(book);
    }

    @Test
    void addBookCopy_ShouldReturnBookCopy() {
        Long isbn = 1234567890L;
        BookCopy copy = new BookCopy();
        when(librarianService.addBookCopy(isbn)).thenReturn(copy);

        ResponseEntity<BookCopy> response = librarianController.addBookCopy(isbn);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(copy, response.getBody());
        verify(librarianService, times(1)).addBookCopy(isbn);
    }

    @Test
    void registerBorrower_ShouldReturnBorrower() {
        Borrower borrower = new Borrower();
        when(librarianService.registerBorrower(borrower)).thenReturn(borrower);

        ResponseEntity<Borrower> response = librarianController.registerBorrower(borrower);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(borrower, response.getBody());
        verify(librarianService, times(1)).registerBorrower(borrower);
    }

    @Test
    void getAvailableBooks_ShouldReturnBookList() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(librarianService.getAvailableBooks()).thenReturn(books);

        ResponseEntity<List<Book>> response = librarianController.getAvailableBooks();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(librarianService, times(1)).getAvailableBooks();
    }
}
