package com.cognizant.mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.ArgumentMatchers.startsWith;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.service.UserRepository;
import com.cognizant.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Argument Matcher Tests")
public class ArgumentMatcherTest {
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }
    
    @Test
    @DisplayName("Test with any() matcher")
    void testAnyMatcher() {
        when(userRepository.findById(anyInt())).thenReturn("Any User");
        
        String result1 = userService.getUserById(1);
        String result2 = userService.getUserById(999);
        
        assertEquals("Any User", result1);
        assertEquals("Any User", result2);
        verify(userRepository, times(2)).findById(anyInt());
    }
    
    @Test
    @DisplayName("Test with anyString() matcher")
    void testAnyStringMatcher() {
        when(userRepository.save(anyString())).thenReturn(true);
        
        boolean result1 = userService.saveUser("Alice");
        boolean result2 = userService.saveUser("Bob");
        
        assertTrue(result1);
        assertTrue(result2);
        verify(userRepository).save("Alice");
        verify(userRepository).save("Bob");
    }
    
    @Test
    @DisplayName("Test with eq() matcher for specific value")
    void testEqMatcher() {
        when(userRepository.findById(eq(1))).thenReturn("John Doe");
        
        String result = userService.getUserById(1);
        
        assertEquals("John Doe", result);
        verify(userRepository).findById(eq(1));
    }
    
    @Test
    @DisplayName("Test with isNull() matcher")
    void testIsNullMatcher() {
        boolean result = userService.saveUser(null);
        
        assertFalse(result);
        verify(userRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Test with isNotNull() matcher")
    void testIsNotNullMatcher() {
        when(userRepository.save(isNotNull())).thenReturn(true);
        
        boolean result = userService.saveUser("Valid User");
        
        assertTrue(result);
        verify(userRepository).save(isNotNull());
    }
    
    @Test
    @DisplayName("Test with contains() matcher")
    void testContainsMatcher() {
        when(userRepository.save(contains("admin"))).thenReturn(true);
        
        userService.saveUser("admin_user");
        
        verify(userRepository).save(contains("admin"));
    }
    
    @Test
    @DisplayName("Test with startsWith() matcher")
    void testStartsWithMatcher() {
        when(userRepository.save(startsWith("test"))).thenReturn(true);
        
        userService.saveUser("test_user");
        
        verify(userRepository).save(startsWith("test"));
    }
    
    @Test
    @DisplayName("Test with endsWith() matcher")
    void testEndsWithMatcher() {
        when(userRepository.save(endsWith("@test.com"))).thenReturn(true);
        
        userService.saveUser("user@test.com");
        
        verify(userRepository).save(endsWith("@test.com"));
    }
    
    @Test
    @DisplayName("Test with matches() matcher for regex")
    void testMatchesMatcher() {
        when(userRepository.save(matches(".*@.*\\.com"))).thenReturn(true);
        
        userService.saveUser("user@example.com");
        
        verify(userRepository).save(matches(".*@.*\\.com"));
    }
    
    @Test
    @DisplayName("Test with argThat() custom matcher")
    void testArgThatMatcher() {
        when(userRepository.save(argThat(s -> s != null && s.length() > 5)))
            .thenReturn(true);
        
        boolean result1 = userService.saveUser("LongUsername");
        
        assertTrue(result1);
        verify(userRepository).save(argThat(s -> s != null && s.length() > 5));
    }
    
    @Test
    @DisplayName("Test argument capture is not needed but verification works")
    void testArgumentVerification() {
        when(userRepository.save(anyString())).thenReturn(true);
        
        userService.saveUser("TestUser");
        
        verify(userRepository).save("TestUser");
    }
    
    @Test
    @DisplayName("Test multiple argument matchers")
    void testMultipleMatchers() {
        userService.getUserById(1);
        userService.getUserById(2);
        userService.saveUser("Alice");
        
        verify(userRepository, times(2)).findById(anyInt());
        verify(userRepository).save(anyString());
    }
    
    @Test
    @DisplayName("Test combining specific value and matcher")
    void testCombiningMatchers() {
        when(userRepository.findById(1)).thenReturn("Specific User");
        
        String result = userService.getUserById(1);
        
        assertEquals("Specific User", result);
        verify(userRepository).findById(1);
    }
    
    @Test
    @DisplayName("Test anyList() matcher")
    void testAnyListMatcher() {
        List<String> users = Arrays.asList("User1", "User2");
        when(userRepository.findAll()).thenReturn(users);
        
        List<String> result = userService.getAllUsers();
        
        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }
}
