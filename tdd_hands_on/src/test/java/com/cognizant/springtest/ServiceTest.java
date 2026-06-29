package com.cognizant.springtest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.service.UserRepository;
import com.cognizant.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Service Tests")
public class ServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        System.out.println("Setting up service test");
    }
    
    @Test
    @DisplayName("Test getUserById returns user from repository")
    void testGetUserById() {
        when(userRepository.findById(1)).thenReturn("John Doe");
        
        String result = userService.getUserById(1);
        
        assertEquals("John Doe", result);
        verify(userRepository, times(1)).findById(1);
    }
    
    @Test
    @DisplayName("Test getUserById with multiple users")
    void testGetMultipleUsers() {
        when(userRepository.findById(1)).thenReturn("Alice");
        when(userRepository.findById(2)).thenReturn("Bob");
        when(userRepository.findById(3)).thenReturn("Charlie");
        
        String user1 = userService.getUserById(1);
        String user2 = userService.getUserById(2);
        String user3 = userService.getUserById(3);
        
        assertEquals("Alice", user1);
        assertEquals("Bob", user2);
        assertEquals("Charlie", user3);
        
        verify(userRepository).findById(1);
        verify(userRepository).findById(2);
        verify(userRepository).findById(3);
    }
    
    @Test
    @DisplayName("Test getAllUsers returns list from repository")
    void testGetAllUsers() {
        List<String> mockUsers = Arrays.asList("Alice", "Bob", "Charlie", "David");
        when(userRepository.findAll()).thenReturn(mockUsers);
        
        List<String> result = userService.getAllUsers();
        
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("Alice", result.get(0));
        assertEquals("Bob", result.get(1));
        assertEquals("Charlie", result.get(2));
        assertEquals("David", result.get(3));
        
        verify(userRepository).findAll();
    }
    
    @Test
    @DisplayName("Test getAllUsers returns empty list")
    void testGetAllUsersEmpty() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());
        
        List<String> result = userService.getAllUsers();
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository).findAll();
    }
    
    @Test
    @DisplayName("Test saveUser with valid username")
    void testSaveUserValid() {
        when(userRepository.save("ValidUser")).thenReturn(true);
        
        boolean result = userService.saveUser("ValidUser");
        
        assertTrue(result);
        verify(userRepository).save("ValidUser");
    }
    
    @Test
    @DisplayName("Test saveUser with null username returns false")
    void testSaveUserNull() {
        boolean result = userService.saveUser(null);
        
        assertFalse(result);
        verify(userRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Test saveUser with empty username returns false")
    void testSaveUserEmpty() {
        boolean result = userService.saveUser("");
        
        assertFalse(result);
        verify(userRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Test saveUser with whitespace-only username returns false")
    void testSaveUserWhitespace() {
        boolean result = userService.saveUser("   ");
        
        assertFalse(result);
        verify(userRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Test saveUser validation logic")
    void testSaveUserValidation() {
        assertFalse(userService.saveUser(null));
        assertFalse(userService.saveUser(""));
        assertFalse(userService.saveUser("  "));
        assertFalse(userService.saveUser("\t"));
        
        verify(userRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Test deleteUser successfully")
    void testDeleteUserSuccess() {
        when(userRepository.delete(1)).thenReturn(true);
        
        boolean result = userService.deleteUser(1);
        
        assertTrue(result);
        verify(userRepository).delete(1);
    }
    
    @Test
    @DisplayName("Test deleteUser fails")
    void testDeleteUserFailure() {
        when(userRepository.delete(999)).thenReturn(false);
        
        boolean result = userService.deleteUser(999);
        
        assertFalse(result);
        verify(userRepository).delete(999);
    }
    
    @Test
    @DisplayName("Test getUserCount returns correct count")
    void testGetUserCount() {
        when(userRepository.count()).thenReturn(10);
        
        int count = userService.getUserCount();
        
        assertEquals(10, count);
        verify(userRepository).count();
    }
    
    @Test
    @DisplayName("Test getUserCount with zero users")
    void testGetUserCountZero() {
        when(userRepository.count()).thenReturn(0);
        
        int count = userService.getUserCount();
        
        assertEquals(0, count);
        verify(userRepository).count();
    }
    
    @Test
    @DisplayName("Test service handles repository returning null")
    void testHandleNullFromRepository() {
        when(userRepository.findById(999)).thenReturn(null);
        
        String result = userService.getUserById(999);
        
        assertNull(result);
        verify(userRepository).findById(999);
    }
    
    @Test
    @DisplayName("Test complete user lifecycle")
    void testUserLifecycle() {
        when(userRepository.save("TestUser")).thenReturn(true);
        when(userRepository.findById(1)).thenReturn("TestUser");
        when(userRepository.count()).thenReturn(1);
        when(userRepository.delete(1)).thenReturn(true);
        
        boolean saveResult = userService.saveUser("TestUser");
        assertTrue(saveResult);
        verify(userRepository).save("TestUser");
        
        String user = userService.getUserById(1);
        assertEquals("TestUser", user);
        verify(userRepository).findById(1);
        
        int count = userService.getUserCount();
        assertEquals(1, count);
        verify(userRepository).count();
        
        boolean deleteResult = userService.deleteUser(1);
        assertTrue(deleteResult);
        verify(userRepository).delete(1);
    }
    
    @Test
    @DisplayName("Test service dependency injection")
    void testDependencyInjection() {
        assertNotNull(userService);
        
        when(userRepository.count()).thenReturn(5);
        int count = userService.getUserCount();
        
        assertEquals(5, count);
        verify(userRepository).count();
    }
    
    @Test
    @DisplayName("Test multiple operations in sequence")
    void testMultipleOperations() {
        when(userRepository.save(anyString())).thenReturn(true);
        when(userRepository.findAll()).thenReturn(Arrays.asList("User1", "User2"));
        when(userRepository.count()).thenReturn(2);
        
        userService.saveUser("User1");
        userService.saveUser("User2");
        List<String> users = userService.getAllUsers();
        int count = userService.getUserCount();
        
        assertEquals(2, users.size());
        assertEquals(2, count);
        
        verify(userRepository, times(2)).save(anyString());
        verify(userRepository).findAll();
        verify(userRepository).count();
    }
    
    @Test
    @DisplayName("Test service behavior with repository exceptions")
    void testRepositoryException() {
        when(userRepository.findById(1)).thenThrow(new RuntimeException("Database error"));
        
        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1);
        });
        
        verify(userRepository).findById(1);
    }
}
