import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Simple Logger implementation for demonstration
class SimpleLogger {
    private String className;
    private DateTimeFormatter formatter;
    
    public SimpleLogger(String className) {
        this.className = className;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    
    public void trace(String message) {
        log("TRACE", message);
    }
    
    public void debug(String message) {
        log("DEBUG", message);
    }
    
    public void info(String message) {
        log("INFO", message);
    }
    
    public void warn(String message) {
        log("WARN", message);
    }
    
    public void error(String message) {
        log("ERROR", message);
    }
    
    public void error(String message, Exception e) {
        log("ERROR", message + " - " + e.getMessage());
    }
    
    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("[%s] [%s] [%s] - %s", 
            timestamp, level, className, message));
    }
}

public class LoggingDemo {
    
    private static final SimpleLogger logger = new SimpleLogger("LoggingDemo");
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    LOGGING DEMO - Exercise 4");
        System.out.println("========================================\n");
        
        logger.info("Starting Logging Demo Application");
        
        demonstrateLoggingLevels();
        demonstrateParameterizedLogging();
        demonstrateExceptionLogging();
        
        logger.info("Logging Demo Application completed successfully");
        
        System.out.println("\n========================================");
        System.out.println("    ALL LOGGING TESTS COMPLETED!");
        System.out.println("========================================");
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
        
        logger.info("User " + username + " attempted login " + loginAttempts + " times");
        
        String action = "UPDATE";
        String resource = "user_profile";
        long duration = 150;
        logger.debug("Action: " + action + ", Resource: " + resource + ", Duration: " + duration + "ms");
    }
    
    public static void demonstrateExceptionLogging() {
        try {
            divideNumbers(10, 0);
        } catch (Exception e) {
            logger.error("Error occurred during division operation", e);
        }
    }
    
    private static int divideNumbers(int numerator, int denominator) {
        logger.debug("Dividing " + numerator + " by " + denominator);
        
        if (denominator == 0) {
            logger.warn("Attempted division by zero");
            throw new ArithmeticException("Cannot divide by zero");
        }
        
        return numerator / denominator;
    }
    
    public double calculateScore(int correct, int total) {
        logger.info("Calculating score: " + correct + " correct out of " + total + " total");
        
        if (total <= 0) {
            logger.error("Invalid total: " + total);
            throw new IllegalArgumentException("Total must be positive");
        }
        
        double score = (double) correct / total * 100;
        logger.debug("Calculated score: " + score);
        
        return score;
    }
}
