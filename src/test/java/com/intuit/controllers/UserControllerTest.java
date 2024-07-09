package com.intuit.controllers;

import com.intuit.models.Users;
import com.intuit.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidUser_UserExists() {
        Users user = new Users();
        user.setEmail("test@example.com");
        when(userService.doesUserExists(user.getEmail())).thenReturn(true);

        ResponseEntity<String> response = userController.validUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User verified successfully.", response.getBody());
        verify(userService, times(1)).doesUserExists(user.getEmail());
    }

    @Test
    void testValidUser_UserDoesNotExist() {
        Users user = new Users();
        user.setEmail("test@example.com");
        when(userService.doesUserExists(user.getEmail())).thenReturn(false);

        ResponseEntity<String> response = userController.validUser(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found or invalid credentials.", response.getBody());
        verify(userService, times(1)).doesUserExists(user.getEmail());
    }

    @Test
    void testGetUser_UserExists() {
        Users user = new Users();
        user.setEmail("test@example.com");
        when(userService.getUser(user.getEmail())).thenReturn(user);

        ResponseEntity<Users> response = userController.getUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUser(user.getEmail());
    }

    @Test
    void testGetUser_UserDoesNotExist() {
        Users user = new Users();
        user.setEmail("test@example.com");
        when(userService.getUser(user.getEmail())).thenReturn(null);

        ResponseEntity<Users> response = userController.getUser(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).getUser(user.getEmail());
    }

    @Test
    void testSignup_Success() {
        Users user = new Users();
        user.setEmail("test@example.com");
        when(userService.createUser(user)).thenReturn(user);

        Users response = userController.signup(user);

        assertEquals(user, response);
        verify(userService, times(1)).createUser(user);
    }

    @Test
    void testUpdateUser_Success() {
        Users user = new Users();
        user.setEmail("test@example.com");
        when(userService.updateUser(user)).thenReturn(user);

        Users response = userController.updateUser(user);

        assertEquals(user, response);
        verify(userService, times(1)).updateUser(user);
    }

    @Test
    void testSignIn_UserExists() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userService.verifyUser(user.getEmail(), user.getPassword())).thenReturn(true);

        ResponseEntity<String> response = userController.signIn(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User verified successfully.", response.getBody());
        verify(userService, times(1)).verifyUser(user.getEmail(), user.getPassword());
    }

    @Test
    void testSignIn_UserDoesNotExist() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword("wrongpassword");
        when(userService.verifyUser(user.getEmail(), user.getPassword())).thenReturn(false);

        ResponseEntity<String> response = userController.signIn(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found or invalid credentials.", response.getBody());
        verify(userService, times(1)).verifyUser(user.getEmail(), user.getPassword());
    }
}
