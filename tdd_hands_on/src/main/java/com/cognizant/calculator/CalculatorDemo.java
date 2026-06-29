package com.cognizant.calculator;

public class CalculatorDemo {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    CALCULATOR DEMO - Module 4");
        System.out.println("========================================\n");
        
        Calculator calculator = new Calculator();
        
        // Addition
        System.out.println("1. Addition Test:");
        int sum = calculator.add(15, 25);
        System.out.println("   15 + 25 = " + sum);
        
        // Subtraction
        System.out.println("\n2. Subtraction Test:");
        int difference = calculator.subtract(50, 20);
        System.out.println("   50 - 20 = " + difference);
        
        // Multiplication
        System.out.println("\n3. Multiplication Test:");
        int product = calculator.multiply(7, 8);
        System.out.println("   7 * 8 = " + product);
        
        // Division
        System.out.println("\n4. Division Test:");
        int quotient = calculator.divide(100, 5);
        System.out.println("   100 / 5 = " + quotient);
        
        // Square Root
        System.out.println("\n5. Square Root Test:");
        double sqrtResult = calculator.squareRoot(64);
        System.out.println("   √64 = " + sqrtResult);
        
        // Even Number Check
        System.out.println("\n6. Even Number Check:");
        boolean isEven10 = calculator.isEven(10);
        boolean isEven7 = calculator.isEven(7);
        System.out.println("   Is 10 even? " + isEven10);
        System.out.println("   Is 7 even? " + isEven7);
        
        // Factorial
        System.out.println("\n7. Factorial Test:");
        long factorial5 = calculator.factorial(5);
        System.out.println("   5! = " + factorial5);
        
        // Exception handling demonstration
        System.out.println("\n8. Exception Handling:");
        try {
            calculator.divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("   Error: " + e.getMessage());
        }
        
        try {
            calculator.squareRoot(-25);
        } catch (IllegalArgumentException e) {
            System.out.println("   Error: " + e.getMessage());
        }
        
        System.out.println("\n========================================");
        System.out.println("    ALL CALCULATOR TESTS COMPLETED!");
        System.out.println("========================================");
    }
}
