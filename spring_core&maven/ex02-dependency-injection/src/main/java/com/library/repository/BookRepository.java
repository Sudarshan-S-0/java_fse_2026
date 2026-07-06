package com.library.repository;

public class BookRepository {
    
    public String findAllBooks() {
        System.out.println("[BookRepository] Executing findAllBooks() method");
        return "Sample Books: 'Spring in Action', 'Effective Java', 'Clean Code'";
    }
}
