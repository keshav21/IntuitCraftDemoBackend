package com.intuit.services;


import com.intuit.components.UserComponent;
import com.intuit.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserComponent userComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoesUserExists_UserExists() {
        String email = "test@example.com";
        when(userComponent.doesUserExists(email)).thenReturn(true);

        boolean result = userService.doesUserExists(email);

        assertTrue(result);
        verify(userComponent, times(1)).doesUserExists(email);
    }

    @Test
    void testDoesUserExists_UserDoesNotExist() {
        String email = "test@example.com";
        when(userComponent.doesUserExists(email)).thenReturn(false);

        boolean result = userService.doesUserExists(email);

        assertFalse(result);
        verify(userComponent, times(1)).doesUserExists(email);
    }

    @Test
    void testCreateUser_Success() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userComponent.createUser(user)).thenReturn(user);

        Users result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userComponent, times(1)).createUser(user);
    }

    @Test
    void testCreateUser_NullUser() {
        Users result = userService.createUser(null);

        assertNull(result);
        verify(userComponent, times(1)).createUser(null);
    }

    @Test
    void testUpdateUser_Success() {
        Users user = new Users();
        user.setEmail("test@example.com");
        when(userComponent.updateUser(user)).thenReturn(user);

        Users result = userService.updateUser(user);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userComponent, times(1)).updateUser(user);
    }

    @Test
    void testUpdateUser_NullUser() {
        Users result = userService.updateUser(null);

        assertNull(result);
        verify(userComponent, times(1)).updateUser(null);
    }

    @Test
    void testGetUser_UserExists() {
        String email = "test@example.com";
        Users user = new Users();
        user.setEmail(email);
        when(userComponent.getUser(email)).thenReturn(user);

        Users result = userService.getUser(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userComponent, times(1)).getUser(email);
    }

    @Test
    void testGetUser_UserDoesNotExist() {
        String email = "test@example.com";
        when(userComponent.getUser(email)).thenReturn(null);

        Users result = userService.getUser(email);

        assertNull(result);
        verify(userComponent, times(1)).getUser(email);
    }

    @Test
    void testVerifyUser_Success() {
        String email = "test@example.com";
        String password = "password";
        Users user = new Users();
        user.setEmail(email);
        user.setPassword("5f4dcc3b5aa765d61d8327deb882cf99"); // MD5 hash for "password"
        when(userComponent.verifyUser(email, password)).thenReturn(true);

        boolean result = userService.verifyUser(email, password);

        assertTrue(result);
        verify(userComponent, times(1)).verifyUser(email, password);
    }

    @Test
    void testVerifyUser_Failure() {
        String email = "test@example.com";
        String password = "wrongpassword";
        when(userComponent.verifyUser(email, password)).thenReturn(false);

        boolean result = userService.verifyUser(email, password);

        assertFalse(result);
        verify(userComponent, times(1)).verifyUser(email, password);
    }
}
