package com.cognizant.service;

import java.util.List;

public class UserService {
    
    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public String getUserById(int id) {
        return userRepository.findById(id);
    }
    
    public List<String> getAllUsers() {
        return userRepository.findAll();
    }
    
    public boolean saveUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return userRepository.save(username);
    }
    
    public boolean deleteUser(int id) {
        return userRepository.delete(id);
    }
    
    public int getUserCount() {
        return userRepository.count();
    }
}
