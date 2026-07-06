package com.library.repository;

public class BookRepository {
    
    public String findAllBooks() {
        System.out.println("BookRepository: Executing findAllBooks() method");
        
        String books = "Sample Books: 'The Great Gatsby', 'To Kill a Mockingbird', '1984'";
        System.out.println("BookRepository: Retrieved books - " + books);
        
        return books;
    }
}
