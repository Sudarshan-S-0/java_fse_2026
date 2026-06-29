package com.cognizant.junit;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.cognizant.calculator.Calculator;

@DisplayName("Parameterized Test Demo")
public class ParameterizedTestDemo {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @ParameterizedTest
    @DisplayName("Test addition with multiple value sets")
    @CsvSource({
        "1, 1, 2",
        "5, 3, 8",
        "10, 20, 30",
        "-5, 5, 0",
        "-10, -20, -30"
    })
    void testAdditionWithCsvSource(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b),
            () -> a + " + " + b + " should equal " + expected);
    }
    
    @ParameterizedTest
    @DisplayName("Test multiplication with value source")
    @ValueSource(ints = {2, 4, 6, 8, 10})
    void testEvenNumbers(int number) {
        assertTrue(calculator.isEven(number), 
            () -> number + " should be even");
    }
    
    @ParameterizedTest
    @DisplayName("Test factorial with multiple inputs")
    @CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "4, 24",
        "5, 120"
    })
    void testFactorial(int input, long expected) {
        assertEquals(expected, calculator.factorial(input));
    }
    
    @ParameterizedTest
    @DisplayName("Test division with CSV source")
    @CsvSource({
        "10, 2, 5",
        "20, 4, 5",
        "100, 10, 10",
        "-10, 2, -5",
        "10, -2, -5"
    })
    void testDivision(int dividend, int divisor, int expected) {
        assertEquals(expected, calculator.divide(dividend, divisor));
    }
    
    @ParameterizedTest
    @DisplayName("Test square root with multiple values")
    @CsvSource({
        "0, 0.0",
        "1, 1.0",
        "4, 2.0",
        "9, 3.0",
        "16, 4.0",
        "25, 5.0"
    })
    void testSquareRoot(double input, double expected) {
        assertEquals(expected, calculator.squareRoot(input), 0.001);
    }
    
    @ParameterizedTest
    @DisplayName("Test with method source")
    @MethodSource("provideNumbersForAddition")
    void testAdditionWithMethodSource(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
    
    static Stream<Arguments> provideNumbersForAddition() {
        return Stream.of(
            Arguments.of(1, 1, 2),
            Arguments.of(2, 3, 5),
            Arguments.of(10, 20, 30),
            Arguments.of(-5, 5, 0),
            Arguments.of(0, 0, 0)
        );
    }
    
    @ParameterizedTest
    @DisplayName("Test subtraction with method source")
    @MethodSource("provideNumbersForSubtraction")
    void testSubtractionWithMethodSource(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }
    
    static Stream<Arguments> provideNumbersForSubtraction() {
        return Stream.of(
            Arguments.of(5, 3, 2),
            Arguments.of(10, 5, 5),
            Arguments.of(0, 0, 0),
            Arguments.of(-5, -3, -2),
            Arguments.of(3, 5, -2)
        );
    }
    
    @ParameterizedTest
    @DisplayName("Test multiplication with various inputs")
    @CsvSource({
        "2, 3, 6",
        "5, 5, 25",
        "10, 0, 0",
        "-2, 3, -6",
        "-2, -3, 6"
    })
    void testMultiplication(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }
    
    @ParameterizedTest(name = "{index} => isEven({0}) = {1}")
    @DisplayName("Test even/odd checker")
    @CsvSource({
        "2, true",
        "3, false",
        "4, true",
        "5, false",
        "0, true",
        "-2, true",
        "-3, false"
    })
    void testIsEven(int number, boolean expected) {
        assertEquals(expected, calculator.isEven(number));
    }
}
