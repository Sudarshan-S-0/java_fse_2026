package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cognizant.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    public UserController() {
    }
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    public String getUser(int id) {
        return userService.getUserById(id);
    }
    
    public List<String> listUsers() {
        return userService.getAllUsers();
    }
    
    public String createUser(String username) {
        boolean success = userService.saveUser(username);
        return success ? "User created successfully" : "Failed to create user";
    }
    
    public String deleteUser(int id) {
        boolean success = userService.deleteUser(id);
        return success ? "User deleted successfully" : "Failed to delete user";
    }
}
