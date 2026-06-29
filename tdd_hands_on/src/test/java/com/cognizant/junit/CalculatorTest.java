package com.cognizant.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognizant.calculator.Calculator;

@DisplayName("Calculator Test Suite")
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeAll
    static void setUpAll() {
        System.out.println("@BeforeAll - Executed once before all tests");
    }
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("@BeforeEach - Setting up calculator instance");
    }
    
    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach - Cleaning up after test");
    }
    
    @AfterAll
    static void tearDownAll() {
        System.out.println("@AfterAll - Executed once after all tests");
    }
    
    @Test
    @DisplayName("Test addition of two positive numbers")
    void testAdd() {
        int result = calculator.add(5, 3);
        assertEquals(8, result, "5 + 3 should equal 8");
    }
    
    @Test
    @DisplayName("Test addition with negative numbers")
    void testAddNegative() {
        assertEquals(-2, calculator.add(-5, 3));
        assertEquals(-8, calculator.add(-5, -3));
    }
    
    @Test
    @DisplayName("Test subtraction")
    void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3));
        assertEquals(-2, calculator.subtract(3, 5));
    }
    
    @Test
    @DisplayName("Test multiplication")
    void testMultiply() {
        assertEquals(15, calculator.multiply(5, 3));
        assertEquals(0, calculator.multiply(5, 0));
        assertEquals(-15, calculator.multiply(-5, 3));
    }
    
    @Test
    @DisplayName("Test division")
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
        assertEquals(-2, calculator.divide(6, -3));
    }
    
    @Test
    @DisplayName("Test division by zero throws exception")
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test square root of positive number")
    void testSquareRoot() {
        assertEquals(5.0, calculator.squareRoot(25), 0.001);
        assertEquals(0.0, calculator.squareRoot(0), 0.001);
    }
    
    @Test
    @DisplayName("Test square root of negative number throws exception")
    void testSquareRootNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.squareRoot(-4);
        });
    }
    
    @Test
    @DisplayName("Test even number checker")
    void testIsEven() {
        assertTrue(calculator.isEven(4), "4 should be even");
        assertTrue(calculator.isEven(0), "0 should be even");
        assertFalse(calculator.isEven(3), "3 should not be even");
        assertFalse(calculator.isEven(-5), "-5 should not be even");
    }
    
    @Test
    @DisplayName("Test factorial calculation")
    void testFactorial() {
        assertEquals(1, calculator.factorial(0));
        assertEquals(1, calculator.factorial(1));
        assertEquals(2, calculator.factorial(2));
        assertEquals(6, calculator.factorial(3));
        assertEquals(24, calculator.factorial(4));
        assertEquals(120, calculator.factorial(5));
    }
    
    @Test
    @DisplayName("Test factorial with negative number throws exception")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.factorial(-1);
        });
    }
    
    @Test
    @Disabled("This test is disabled for demonstration purposes")
    @DisplayName("Disabled test example")
    void testDisabled() {
        fail("This test should not run");
    }
    
    @Test
    @DisplayName("Test multiple assertions")
    void testMultipleAssertions() {
        assertAll("Calculator operations",
            () -> assertEquals(8, calculator.add(5, 3), "Addition failed"),
            () -> assertEquals(2, calculator.subtract(5, 3), "Subtraction failed"),
            () -> assertEquals(15, calculator.multiply(5, 3), "Multiplication failed"),
            () -> assertEquals(5, calculator.divide(15, 3), "Division failed")
        );
    }
}
