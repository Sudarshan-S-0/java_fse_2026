package com.cognizant.junit;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import com.cognizant.calculator.Calculator;

@DisplayName("Timeout Test Demo")
public class TimeoutTestDemo {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Test method completes within timeout using annotation")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testAdditionWithTimeout() {
        int result = calculator.add(5, 3);
        assertEquals(8, result);
    }
    
    @Test
    @DisplayName("Test method with assertTimeout")
    void testMultiplicationWithAssertTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            int result = calculator.multiply(5, 3);
            assertEquals(15, result);
        });
    }
    
    @Test
    @DisplayName("Test factorial calculation completes quickly")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testFactorialWithTimeout() {
        long result = calculator.factorial(10);
        assertEquals(3628800, result);
    }
    
    @Test
    @DisplayName("Test division with assertTimeout")
    void testDivisionWithAssertTimeout() {
        assertTimeout(Duration.ofMillis(50), () -> {
            calculator.divide(100, 5);
        }, "Division should complete quickly");
    }
    
    @Test
    @DisplayName("Test assertTimeoutPreemptively aborts after timeout")
    void testWithAssertTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            calculator.add(1, 2);
        }, "Operation should complete before timeout");
    }
    
    @Test
    @DisplayName("Test multiple operations complete within timeout")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testMultipleOperations() {
        assertAll("Multiple operations",
            () -> assertEquals(8, calculator.add(5, 3)),
            () -> assertEquals(15, calculator.multiply(5, 3)),
            () -> assertEquals(2, calculator.subtract(5, 3)),
            () -> assertEquals(5, calculator.divide(15, 3))
        );
    }
    
    @Test
    @DisplayName("Test square root calculation with timeout")
    void testSquareRootWithTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            double result = calculator.squareRoot(144);
            assertEquals(12.0, result, 0.001);
        });
    }
    
    @Test
    @DisplayName("Test loop operations complete within timeout")
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testLoopOperationsWithTimeout() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum = calculator.add(sum, i);
        }
        assertEquals(5050, sum);
    }
    
    @Test
    @DisplayName("Test that returns a value within timeout")
    void testReturningValueWithTimeout() {
        String result = assertTimeout(Duration.ofMillis(100), () -> {
            calculator.multiply(2, 3);
            return "Completed";
        });
        assertEquals("Completed", result);
    }
    
    @Test
    @DisplayName("Test timeout with message supplier")
    void testTimeoutWithMessageSupplier() {
        assertTimeout(
            Duration.ofMillis(100),
            () -> calculator.factorial(5),
            () -> "Factorial calculation exceeded timeout"
        );
    }
    
    @Test
    @DisplayName("Simulate slow operation - should pass")
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testSlowOperation() throws InterruptedException {
        Thread.sleep(100);
        int result = calculator.add(10, 20);
        assertEquals(30, result);
    }
    
    @Test
    @DisplayName("Test factorial of larger number with reasonable timeout")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testLargerFactorial() {
        long result = calculator.factorial(15);
        assertTrue(result > 0, "Factorial should be positive");
    }
}
