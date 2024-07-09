package com.intuit.components;

import com.intuit.models.Users;
import com.intuit.models.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserComponentTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserComponent userComponent;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testDoesUserExists() {
        Users user = new Users();
        user.setEmail("test@example.com");

        when(usersRepository.findByEmail(anyString())).thenReturn(user);

        boolean exists = userComponent.doesUserExists("test@example.com");

        assertTrue(exists);
    }

    @Test
    void testDoesNotUserExist() {
        when(usersRepository.findByEmail(anyString())).thenReturn(null);

        boolean exists = userComponent.doesUserExists("test@example.com");

        assertFalse(exists);
    }

    @Test
    void testCreateUser() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(usersRepository.save(any(Users.class))).thenReturn(user);

        Users createdUser = userComponent.createUser(user);

        assertNotNull(createdUser);
        assertEquals("test@example.com", createdUser.getEmail());
        assertNotEquals("password", createdUser.getPassword());
    }

    @Test
    void testUpdateUser() {
        Users user = new Users();
        user.setEmail("test@example.com");

        when(usersRepository.save(any(Users.class))).thenReturn(user);

        Users updatedUser = userComponent.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals("test@example.com", updatedUser.getEmail());
    }

    @Test
    void testGetUserWhenUserIsFound() {
        Users user = new Users();
        user.setEmail("test@example.com");

        when(usersRepository.findByEmail(anyString())).thenReturn(user);

        Users foundUser = userComponent.getUser("test@example.com");

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void testGetUserWhenUserIsNotFound() {
        when(usersRepository.findByEmail(anyString())).thenReturn(null);

        Users foundUser = userComponent.getUser("test@example.com");

        assertNull(foundUser);
    }

    @Test
    void testVerifyUser() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword(userComponent.hashPassword("password"));

        when(usersRepository.findByEmail(anyString())).thenReturn(user);

        boolean verified = userComponent.verifyUser("test@example.com", "password");

        assertTrue(verified);
    }

    @Test
    void testVerifyUser_UserIsNotVerified() {
        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword(userComponent.hashPassword("password"));

        when(usersRepository.findByEmail(anyString())).thenReturn(user);

        boolean verified = userComponent.verifyUser("test@example.com", "wrongpassword");

        assertFalse(verified);
    }

    @Test
    void testVerifyUser_UserNotFound() {
        when(usersRepository.findByEmail(anyString())).thenReturn(null);

        boolean verified = userComponent.verifyUser("test@example.com", "password");

        assertFalse(verified);
    }
}
