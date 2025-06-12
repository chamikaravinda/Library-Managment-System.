package com.library.management.controller;

import com.library.management.model.BorrowRecord;
import com.library.management.service.BorrowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowerControllerTest {

    @Mock
    private BorrowerService borrowerService;

    @InjectMocks
    private BorrowerController borrowerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void borrowBook_ShouldReturnBorrowRecord() {
        Long bookId = 1L;
        Long borrowerId = 2L;
        BorrowRecord mockRecord = new BorrowRecord(); // You can set properties as needed

        when(borrowerService.borrowBook(bookId, borrowerId)).thenReturn(mockRecord);

        ResponseEntity<BorrowRecord> response = borrowerController.borrowBook(bookId, borrowerId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockRecord, response.getBody());
        verify(borrowerService, times(1)).borrowBook(bookId, borrowerId);
    }

    @Test
    void returnBook_ShouldReturnBorrowRecord() {
        Long bookId = 3L;
        Long borrowerId = 4L;
        BorrowRecord mockRecord = new BorrowRecord(); // Customize if needed

        when(borrowerService.returnBook(bookId, borrowerId)).thenReturn(mockRecord);

        ResponseEntity<BorrowRecord> response = borrowerController.returnBook(bookId, borrowerId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockRecord, response.getBody());
        verify(borrowerService, times(1)).returnBook(bookId, borrowerId);
    }
}
