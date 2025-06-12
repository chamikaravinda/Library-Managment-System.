package com.library.management.service;

import com.library.management.model.Book;
import com.library.management.model.BookCopy;
import com.library.management.model.Borrower;
import com.library.management.model.User;
import com.library.management.repository.BookCopyRepository;
import com.library.management.repository.BookRepository;
import com.library.management.repository.BorrowerRepository;
import com.library.management.repository.UserRepository;
import com.library.management.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibrarianService {

    private final BookRepository bookRepository;

    private final BookCopyRepository bookCopyRepository;

    private final BorrowerRepository borrowerRepository;

    private final UserRepository userRepository;

    public LibrarianService(BookRepository bookRepository, BookCopyRepository bookCopyRepository,
                            BorrowerRepository borrowerRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.borrowerRepository = borrowerRepository;
        this.userRepository = userRepository;
    }

    public Book addBook(Book book) {
        bookRepository.findByIsbn(book.getIsbn()).ifPresent(existing -> {
            if (!existing.getAuthor().equals(book.getAuthor()) || !existing.getTitle().equals(book.getTitle())) {
                throw new IllegalArgumentException("ISBN conflict with different book details");
            }
        });
        return bookRepository.save(book);
    }

    public BookCopy addBookCopy(Long isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new NoSuchElementException("Book not found"));
        BookCopy copy = new BookCopy();
        copy.setBook(book);
        return bookCopyRepository.save(copy);
    }

    @Transactional
    public Borrower registerBorrower(Borrower borrower) {
        User user = new User();
        user.setRole(Roles.BORROWER.toString());
        user.setPassword("dummy");
        user.setUsername(borrower.getEmail());
        userRepository.save(user);

        borrower.setUser(user);
        return borrowerRepository.save(borrower);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAll();
    }
}
