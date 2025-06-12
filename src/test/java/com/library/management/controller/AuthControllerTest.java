package com.library.management.controller;

import com.library.management.dto.AuthRequest;
import com.library.management.dto.AuthResponse;
import com.library.management.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnAuthResponse_WhenAuthenticated() {
        AuthRequest request = new AuthRequest();
        String expectedToken = "mock-token";

        when(authService.authenticate(request)).thenReturn(expectedToken);

        ResponseEntity<AuthResponse> response = authController.login(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Authenticated Successfully", response.getBody().getMessage());
        assertEquals(expectedToken, response.getBody().getToken());

        verify(authService, times(1)).authenticate(request);
    }
}
