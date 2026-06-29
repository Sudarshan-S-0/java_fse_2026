package com.cognizant.springtest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.controller.UserController;
import com.cognizant.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Controller Tests")
public class ControllerTest {
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    void setUp() {
        System.out.println("Setting up controller test");
    }
    
    @Test
    @DisplayName("Test getUser returns user from service")
    void testGetUser() {
        when(userService.getUserById(1)).thenReturn("John Doe");
        
        String result = userController.getUser(1);
        
        assertEquals("John Doe", result);
        verify(userService).getUserById(1);
    }
    
    @Test
    @DisplayName("Test getUser with non-existent user")
    void testGetUserNotFound() {
        when(userService.getUserById(999)).thenReturn(null);
        
        String result = userController.getUser(999);
        
        assertNull(result);
        verify(userService).getUserById(999);
    }
    
    @Test
    @DisplayName("Test listUsers returns all users")
    void testListUsers() {
        List<String> mockUsers = Arrays.asList("Alice", "Bob", "Charlie");
        when(userService.getAllUsers()).thenReturn(mockUsers);
        
        List<String> result = userController.listUsers();
        
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("Charlie"));
        verify(userService).getAllUsers();
    }
    
    @Test
    @DisplayName("Test listUsers returns empty list")
    void testListUsersEmpty() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList());
        
        List<String> result = userController.listUsers();
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userService).getAllUsers();
    }
    
    @Test
    @DisplayName("Test createUser successfully")
    void testCreateUserSuccess() {
        when(userService.saveUser("NewUser")).thenReturn(true);
        
        String result = userController.createUser("NewUser");
        
        assertEquals("User created successfully", result);
        verify(userService).saveUser("NewUser");
    }
    
    @Test
    @DisplayName("Test createUser fails")
    void testCreateUserFailure() {
        when(userService.saveUser("InvalidUser")).thenReturn(false);
        
        String result = userController.createUser("InvalidUser");
        
        assertEquals("Failed to create user", result);
        verify(userService).saveUser("InvalidUser");
    }
    
    @Test
    @DisplayName("Test createUser with null username")
    void testCreateUserNull() {
        when(userService.saveUser(null)).thenReturn(false);
        
        String result = userController.createUser(null);
        
        assertEquals("Failed to create user", result);
        verify(userService).saveUser(null);
    }
    
    @Test
    @DisplayName("Test deleteUser successfully")
    void testDeleteUserSuccess() {
        when(userService.deleteUser(1)).thenReturn(true);
        
        String result = userController.deleteUser(1);
        
        assertEquals("User deleted successfully", result);
        verify(userService).deleteUser(1);
    }
    
    @Test
    @DisplayName("Test deleteUser fails")
    void testDeleteUserFailure() {
        when(userService.deleteUser(999)).thenReturn(false);
        
        String result = userController.deleteUser(999);
        
        assertEquals("Failed to delete user", result);
        verify(userService).deleteUser(999);
    }
    
    @Test
    @DisplayName("Test controller handles multiple requests")
    void testMultipleRequests() {
        when(userService.getUserById(1)).thenReturn("User1");
        when(userService.getUserById(2)).thenReturn("User2");
        when(userService.saveUser("User3")).thenReturn(true);
        
        String user1 = userController.getUser(1);
        String user2 = userController.getUser(2);
        String createResult = userController.createUser("User3");
        
        assertEquals("User1", user1);
        assertEquals("User2", user2);
        assertEquals("User created successfully", createResult);
        
        verify(userService).getUserById(1);
        verify(userService).getUserById(2);
        verify(userService).saveUser("User3");
    }
    
    @Test
    @DisplayName("Test controller dependency injection")
    void testDependencyInjection() {
        assertNotNull(userController);
        
        when(userService.getUserById(1)).thenReturn("Test");
        String result = userController.getUser(1);
        
        assertEquals("Test", result);
    }
    
    @Test
    @DisplayName("Test all CRUD operations")
    void testCRUDOperations() {
        when(userService.saveUser("TestUser")).thenReturn(true);
        when(userService.getUserById(1)).thenReturn("TestUser");
        when(userService.getAllUsers()).thenReturn(Arrays.asList("TestUser"));
        when(userService.deleteUser(1)).thenReturn(true);
        
        String createResult = userController.createUser("TestUser");
        assertEquals("User created successfully", createResult);
        
        String getResult = userController.getUser(1);
        assertEquals("TestUser", getResult);
        
        List<String> listResult = userController.listUsers();
        assertEquals(1, listResult.size());
        
        String deleteResult = userController.deleteUser(1);
        assertEquals("User deleted successfully", deleteResult);
        
        verify(userService).saveUser("TestUser");
        verify(userService).getUserById(1);
        verify(userService).getAllUsers();
        verify(userService).deleteUser(1);
    }
}
