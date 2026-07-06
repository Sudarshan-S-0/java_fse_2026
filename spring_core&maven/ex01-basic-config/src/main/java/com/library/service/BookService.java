package com.library.service;

public class BookService {
    
    public String displayMessage() {
        String message = "BookService: Service bean has been successfully instantiated and is ready!";
        System.out.println("BookService.displayMessage() executed");
        System.out.println(message);
        return message;
    }
}
