package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    
    private BookRepository bookRepository;
    
    public void setBookRepository(BookRepository repository) {
        this.bookRepository = repository;
        System.out.println("[BookService] BookRepository dependency injected via setter");
    }
    
    public String performBookOperation() {
        System.out.println("[BookService] Executing performBookOperation() method");
        
        if (bookRepository != null) {
            System.out.println("[BookService] Calling BookRepository.findAllBooks()");
            String books = bookRepository.findAllBooks();
            System.out.println("[BookService] Successfully retrieved books from repository");
            return books;
        } else {
            System.out.println("[BookService] Warning: BookRepository is null!");
            return "No repository available";
        }
    }
}
