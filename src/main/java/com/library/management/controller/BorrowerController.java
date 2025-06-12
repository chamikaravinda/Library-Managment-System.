package com.library.management.controller;

import com.library.management.model.BorrowRecord;
import com.library.management.service.BorrowerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrower")
@Tag(name="Borrower")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PatchMapping("/borrow/{bookId}/{borrowerId}")
    public ResponseEntity<BorrowRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        return ResponseEntity.ok(borrowerService.borrowBook(bookId, borrowerId));
    }

    @PatchMapping("/return/{bookId}/{borrowerId}")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        return ResponseEntity.ok(borrowerService.returnBook(bookId,borrowerId));
    }
}
