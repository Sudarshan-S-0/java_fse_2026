package com.cognizant.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InOrder;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.service.UserRepository;
import com.cognizant.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Verify Interaction Tests")
public class VerifyInteractionTest {
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }
    
    @Test
    @DisplayName("Test verify method was called once")
    void testVerifyMethodCalledOnce() {
        when(userRepository.findById(1)).thenReturn("John");
        
        userService.getUserById(1);
        
        verify(userRepository, times(1)).findById(1);
        verify(userRepository).findById(1);
    }
    
    @Test
    @DisplayName("Test verify method was called multiple times")
    void testVerifyMethodCalledMultipleTimes() {
        when(userRepository.count()).thenReturn(5);
        
        userService.getUserCount();
        userService.getUserCount();
        userService.getUserCount();
        
        verify(userRepository, times(3)).count();
    }
    
    @Test
    @DisplayName("Test verify method was never called")
    void testVerifyMethodNeverCalled() {
        userService.getUserById(1);
        
        verify(userRepository, never()).delete(anyInt());
        verify(userRepository, never()).save(anyString());
    }
    
    @Test
    @DisplayName("Test verify method called at least once")
    void testVerifyAtLeastOnce() {
        when(userRepository.findById(1)).thenReturn("John");
        
        userService.getUserById(1);
        userService.getUserById(1);
        
        verify(userRepository, atLeastOnce()).findById(1);
    }
    
    @Test
    @DisplayName("Test verify method called at least N times")
    void testVerifyAtLeast() {
        when(userRepository.count()).thenReturn(10);
        
        userService.getUserCount();
        userService.getUserCount();
        userService.getUserCount();
        
        verify(userRepository, atLeast(2)).count();
    }
    
    @Test
    @DisplayName("Test verify method called at most N times")
    void testVerifyAtMost() {
        when(userRepository.findById(1)).thenReturn("John");
        
        userService.getUserById(1);
        userService.getUserById(1);
        
        verify(userRepository, atMost(3)).findById(1);
    }
    
    @Test
    @DisplayName("Test verify no interactions with mock")
    void testVerifyNoInteractions() {
        verifyNoInteractions(userRepository);
    }
    
    @Test
    @DisplayName("Test verify no more interactions after specified ones")
    void testVerifyNoMoreInteractions() {
        when(userRepository.findById(1)).thenReturn("John");
        
        userService.getUserById(1);
        
        verify(userRepository).findById(1);
        verifyNoMoreInteractions(userRepository);
    }
    
    @Test
    @DisplayName("Test verify interaction order")
    void testVerifyInOrder() {
        when(userRepository.save("Alice")).thenReturn(true);
        when(userRepository.findById(1)).thenReturn("Alice");
        when(userRepository.delete(1)).thenReturn(true);
        
        userService.saveUser("Alice");
        userService.getUserById(1);
        userService.deleteUser(1);
        
        InOrder inOrder = inOrder(userRepository);
        inOrder.verify(userRepository).save("Alice");
        inOrder.verify(userRepository).findById(1);
        inOrder.verify(userRepository).delete(1);
    }
    
    @Test
    @DisplayName("Test verify specific arguments were used")
    void testVerifySpecificArguments() {
        when(userRepository.save(anyString())).thenReturn(true);
        
        userService.saveUser("TestUser");
        
        verify(userRepository).save("TestUser");
    }
    
    @Test
    @DisplayName("Test verify with argument matchers")
    void testVerifyWithMatchers() {
        when(userRepository.findById(anyInt())).thenReturn("User");
        
        userService.getUserById(5);
        
        verify(userRepository).findById(anyInt());
        verify(userRepository).findById(eq(5));
    }
    
    @Test
    @DisplayName("Test verify exact number of invocations")
    void testVerifyExactInvocations() {
        when(userRepository.findById(anyInt())).thenReturn("User");
        
        userService.getUserById(1);
        userService.getUserById(2);
        
        verify(userRepository, times(2)).findById(anyInt());
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(2);
    }
    
    @Test
    @DisplayName("Test verify only specific interactions")
    void testVerifyOnly() {
        when(userRepository.count()).thenReturn(5);
        
        userService.getUserCount();
        
        verify(userRepository, only()).count();
    }
    
    @Test
    @DisplayName("Test complex interaction verification")
    void testComplexInteractionVerification() {
        when(userRepository.save(anyString())).thenReturn(true);
        when(userRepository.findById(anyInt())).thenReturn("User");
        when(userRepository.count()).thenReturn(1);
        
        userService.saveUser("Alice");
        userService.getUserById(1);
        userService.getUserCount();
        
        verify(userRepository).save("Alice");
        verify(userRepository).findById(1);
        verify(userRepository).count();
        verifyNoMoreInteractions(userRepository);
    }
    
    @Test
    @DisplayName("Test verify timeout for async operations simulation")
    void testVerifyWithTimeout() {
        when(userRepository.findById(1)).thenReturn("John");
        
        userService.getUserById(1);
        
        verify(userRepository, timeout(1000)).findById(1);
    }
}
