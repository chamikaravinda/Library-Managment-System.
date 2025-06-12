package com.library.management.service;

import com.library.management.model.BookCopy;
import com.library.management.model.BorrowRecord;
import com.library.management.model.Borrower;
import com.library.management.repository.BookCopyRepository;
import com.library.management.repository.BorrowRecordRepository;
import com.library.management.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BorrowerService {

    private final BookCopyRepository bookCopyRepository;

    private final BorrowerRepository borrowerRepository;

    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowerService(BookCopyRepository bookCopyRepository, BorrowerRepository borrowerRepository,
                           BorrowRecordRepository borrowRecordRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.borrowerRepository = borrowerRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public BorrowRecord borrowBook(Long bookId, Long borrowerId) {

        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new NoSuchElementException("Borrower not found"));

        List<BookCopy> availableCopies = bookCopyRepository.findByBookIdAndBorrowedFalse(bookId);
        if (availableCopies.isEmpty()) {
            throw new IllegalStateException("No copies available");
        }

        BookCopy copy = availableCopies.get(0);
        copy.setBorrowed(true);
        bookCopyRepository.save(copy);

        BorrowRecord record = new BorrowRecord();
        record.setBookCopy(copy);
        record.setBorrower(borrower);
        record.setBorrowDate(LocalDate.now());

        return borrowRecordRepository.save(record);
    }

    public BorrowRecord returnBook(Long bookId, Long borrowerId) {
        BorrowRecord record = borrowRecordRepository.findByBorrowerId(borrowerId).stream()
                .filter(r -> r.getBookCopy().getBook().getId().equals(bookId) && r.getReturnDate() == null)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Borrow record not found"));

        BookCopy copy = bookCopyRepository.findById(record.getBookCopy().getId())
                .orElseThrow(() -> new NoSuchElementException("Copy not found"));

        copy.setBorrowed(false);
        bookCopyRepository.save(copy);

        record.setReturnDate(LocalDate.now());
        return borrowRecordRepository.save(record);
    }
}
