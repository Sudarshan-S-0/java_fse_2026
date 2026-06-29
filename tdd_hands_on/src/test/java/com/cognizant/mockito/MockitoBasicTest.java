package com.cognizant.mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.service.UserRepository;
import com.cognizant.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Basic Tests")
public class MockitoBasicTest {
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }
    
    @Test
    @DisplayName("Test getUserById returns mocked value")
    void testGetUserById() {
        when(userRepository.findById(1)).thenReturn("John Doe");
        
        String result = userService.getUserById(1);
        
        assertEquals("John Doe", result);
        verify(userRepository, times(1)).findById(1);
    }
    
    @Test
    @DisplayName("Test getAllUsers returns mocked list")
    void testGetAllUsers() {
        List<String> mockUsers = Arrays.asList("Alice", "Bob", "Charlie");
        when(userRepository.findAll()).thenReturn(mockUsers);
        
        List<String> result = userService.getAllUsers();
        
        assertEquals(3, result.size());
        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("Bob"));
        verify(userRepository).findAll();
    }
    
    @Test
    @DisplayName("Test saveUser with valid username")
    void testSaveUserValid() {
        when(userRepository.save("John")).thenReturn(true);
        
        boolean result = userService.saveUser("John");
        
        assertTrue(result);
        verify(userRepository).save("John");
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
        boolean result = userService.saveUser("   ");
        
        assertFalse(result);
        verify(userRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Test deleteUser success")
    void testDeleteUser() {
        when(userRepository.delete(1)).thenReturn(true);
        
        boolean result = userService.deleteUser(1);
        
        assertTrue(result);
        verify(userRepository).delete(1);
    }
    
    @Test
    @DisplayName("Test getUserCount returns mocked count")
    void testGetUserCount() {
        when(userRepository.count()).thenReturn(5);
        
        int count = userService.getUserCount();
        
        assertEquals(5, count);
        verify(userRepository, times(1)).count();
    }
    
    @Test
    @DisplayName("Test multiple interactions with mock")
    void testMultipleInteractions() {
        when(userRepository.findById(1)).thenReturn("User1");
        when(userRepository.findById(2)).thenReturn("User2");
        
        String user1 = userService.getUserById(1);
        String user2 = userService.getUserById(2);
        
        assertEquals("User1", user1);
        assertEquals("User2", user2);
        verify(userRepository).findById(1);
        verify(userRepository).findById(2);
    }
    
    @Test
    @DisplayName("Test mock returns null by default")
    void testMockDefaultBehavior() {
        String result = userService.getUserById(999);
        
        assertNull(result);
        verify(userRepository).findById(999);
    }
    
    @Test
    @DisplayName("Test verifyNoMoreInteractions")
    void testVerifyNoMoreInteractions() {
        when(userRepository.findById(1)).thenReturn("John");
        
        userService.getUserById(1);
        
        verify(userRepository).findById(1);
        verifyNoMoreInteractions(userRepository);
    }
    
    @Test
    @DisplayName("Test stubbing with different return values")
    void testStubbingMultipleReturnValues() {
        when(userRepository.count())
            .thenReturn(1)
            .thenReturn(2)
            .thenReturn(3);
        
        assertEquals(1, userService.getUserCount());
        assertEquals(2, userService.getUserCount());
        assertEquals(3, userService.getUserCount());
        
        verify(userRepository, times(3)).count();
    }
}
