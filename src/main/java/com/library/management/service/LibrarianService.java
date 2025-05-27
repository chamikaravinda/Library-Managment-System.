package com.library.management.service;

import com.library.management.model.Book;
import com.library.management.model.BookCopy;
import com.library.management.model.Borrower;
import com.library.management.repository.BookCopyRepository;
import com.library.management.repository.BookRepository;
import com.library.management.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibrarianService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    public Book addBook(Book book) {
        bookRepository.findByIsbn(book.getIsbn()).ifPresent(existing -> {
            if (!existing.getAuthor().equals(book.getAuthor()) || !existing.getTitle().equals(book.getTitle())) {
                throw new IllegalArgumentException("ISBN conflict with different book details");
            }
        });
        return bookRepository.save(book);
    }

    public BookCopy addBookCopy(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new NoSuchElementException("Book not found"));
        BookCopy copy = new BookCopy();
        copy.setBook(book);
        return bookCopyRepository.save(copy);
    }

    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAll();
    }
}
