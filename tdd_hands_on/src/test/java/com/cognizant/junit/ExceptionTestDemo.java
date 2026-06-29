package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognizant.calculator.Calculator;

@DisplayName("Exception Testing Demo")
public class ExceptionTestDemo {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Test ArithmeticException is thrown for division by zero")
    void testDivisionByZeroThrowsArithmeticException() {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> calculator.divide(10, 0),
            "Expected divide() to throw ArithmeticException"
        );
        
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test IllegalArgumentException for negative square root")
    void testNegativeSquareRootThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.squareRoot(-16)
        );
        
        assertTrue(exception.getMessage().contains("negative"));
    }
    
    @Test
    @DisplayName("Test IllegalArgumentException for negative factorial")
    void testNegativeFactorialThrowsException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.factorial(-5),
            "Factorial of negative number should throw exception"
        );
    }
    
    @Test
    @DisplayName("Test exception is not thrown for valid operations")
    void testNoExceptionForValidOperations() {
        assertDoesNotThrow(() -> {
            calculator.divide(10, 2);
            calculator.squareRoot(25);
            calculator.factorial(5);
        }, "Valid operations should not throw exceptions");
    }
    
    @Test
    @DisplayName("Test exception message contains expected text")
    void testExceptionMessage() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.factorial(-1)
        );
        
        String expectedMessage = "Factorial not defined for negative numbers";
        String actualMessage = exception.getMessage();
        
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    @DisplayName("Test multiple exception scenarios")
    void testMultipleExceptions() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
        assertThrows(IllegalArgumentException.class, () -> calculator.squareRoot(-1));
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-1));
    }
    
    @Test
    @DisplayName("Test exception type hierarchy")
    void testExceptionTypeHierarchy() {
        assertThrows(RuntimeException.class, () -> calculator.divide(10, 0));
        assertThrows(RuntimeException.class, () -> calculator.squareRoot(-1));
    }
    
    @Test
    @DisplayName("Test exception with assertAll")
    void testExceptionsWithAssertAll() {
        assertAll("Exception tests",
            () -> assertThrows(ArithmeticException.class, 
                () -> calculator.divide(1, 0)),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> calculator.squareRoot(-1)),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> calculator.factorial(-1))
        );
    }
    
    @Test
    @DisplayName("Test exception is thrown and caught")
    void testExceptionThrownAndCaught() {
        try {
            calculator.divide(10, 0);
            fail("Expected ArithmeticException to be thrown");
        } catch (ArithmeticException e) {
            assertEquals("Cannot divide by zero", e.getMessage());
        }
    }
}
