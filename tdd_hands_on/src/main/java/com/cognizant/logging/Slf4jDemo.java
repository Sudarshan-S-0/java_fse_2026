package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo {
    
    private static final Logger logger = LoggerFactory.getLogger(Slf4jDemo.class);
    
    public static void main(String[] args) {
        logger.info("Starting SLF4J Demo Application");
        
        demonstrateLoggingLevels();
        demonstrateParameterizedLogging();
        demonstrateExceptionLogging();
        
        logger.info("SLF4J Demo Application completed successfully");
    }
    
    public static void demonstrateLoggingLevels() {
        logger.trace("This is a TRACE level message - most detailed");
        logger.debug("This is a DEBUG level message - for debugging");
        logger.info("This is an INFO level message - general information");
        logger.warn("This is a WARN level message - potential issues");
        logger.error("This is an ERROR level message - error conditions");
    }
    
    public static void demonstrateParameterizedLogging() {
        String username = "john_doe";
        int loginAttempts = 3;
        
        logger.info("User {} attempted login {} times", username, loginAttempts);
        
        String action = "UPDATE";
        String resource = "user_profile";
        long duration = 150;
        logger.debug("Action: {}, Resource: {}, Duration: {}ms", action, resource, duration);
    }
    
    public static void demonstrateExceptionLogging() {
        try {
            divideNumbers(10, 0);
        } catch (Exception e) {
            logger.error("Error occurred during division operation", e);
        }
    }
    
    private static int divideNumbers(int numerator, int denominator) {
        logger.debug("Dividing {} by {}", numerator, denominator);
        
        if (denominator == 0) {
            logger.warn("Attempted division by zero");
            throw new ArithmeticException("Cannot divide by zero");
        }
        
        return numerator / denominator;
    }
    
    public double calculateScore(int correct, int total) {
        logger.info("Calculating score: {} correct out of {} total", correct, total);
        
        if (total <= 0) {
            logger.error("Invalid total: {}", total);
            throw new IllegalArgumentException("Total must be positive");
        }
        
        double score = (double) correct / total * 100;
        logger.debug("Calculated score: {}", score);
        
        return score;
    }
}
