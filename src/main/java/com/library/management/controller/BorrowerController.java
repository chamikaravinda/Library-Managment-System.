package com.library.management.controller;

import com.library.management.model.BorrowRecord;
import com.library.management.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrower")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @PostMapping("/borrow/{bookId}/{borrowerId}")
    public ResponseEntity<BorrowRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        return ResponseEntity.ok(borrowerService.borrowBook(bookId, borrowerId));
    }

    @PostMapping("/return/{bookId}/{borrowerId}")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        return ResponseEntity.ok(borrowerService.returnBook(bookId,borrowerId));
    }
}
