package com.library;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.repository.BookRepository;
import com.library.service.BookService;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Exercise 1: Basic Spring Configuration");
        System.out.println("========================================");
        System.out.println();
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        System.out.println("Spring context loaded successfully!");
        System.out.println();
        
        BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
        System.out.println("Retrieved bookRepository bean from Spring context");
        System.out.println();
        
        bookRepository.findAllBooks();
        System.out.println();
        
        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println("Retrieved bookService bean from Spring context");
        System.out.println();
        
        bookService.displayMessage();
        System.out.println();
        
        context.close();
        System.out.println("Spring context closed successfully");
        System.out.println();
        System.out.println("Exercise 1 execution completed!");
    }
}
