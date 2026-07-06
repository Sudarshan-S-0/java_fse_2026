package com.library;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.service.BookService;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("Exercise 2: Dependency Injection");
        System.out.println("================================================");
        System.out.println();
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        System.out.println("Spring context loaded successfully!");
        System.out.println();
        
        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println("Retrieved bookService bean from Spring context");
        System.out.println();
        
        System.out.println("Verifying dependency injection...");
        String result = bookService.performBookOperation();
        System.out.println("Result: " + result);
        System.out.println();
        
        System.out.println("Dependency injection successful!");
        System.out.println();
        
        context.close();
        System.out.println("Spring context closed successfully");
        System.out.println();
        System.out.println("Exercise 2 execution completed!");
    }
}
