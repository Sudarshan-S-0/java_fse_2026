package com.cognizant.controller;

import com.cognizant.service.UserService;
import com.cognizant.service.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Mock implementation for demonstration
class MockUserRepository implements UserRepository {
    private Map<Integer, String> users = new HashMap<>();
    private int nextId = 1;
    
    public MockUserRepository() {
        users.put(1, "Alice");
        users.put(2, "Bob");
        users.put(3, "Charlie");
        nextId = 4;
    }
    
    @Override
    public String findById(int id) {
        return users.get(id);
    }
    
    @Override
    public List<String> findAll() {
        return new ArrayList<>(users.values());
    }
    
    @Override
    public boolean save(String username) {
        users.put(nextId++, username);
        return true;
    }
    
    @Override
    public boolean delete(int id) {
        return users.remove(id) != null;
    }
    
    @Override
    public int count() {
        return users.size();
    }
}

public class UserControllerDemo {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  USER CONTROLLER DEMO - Module 4");
        System.out.println("========================================\n");
        
        // Setup
        UserRepository repository = new MockUserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service);
        
        // 1. List all users
        System.out.println("1. Listing All Users:");
        List<String> users = controller.listUsers();
        for (String user : users) {
            System.out.println("   - " + user);
        }
        
        // 2. Get specific user
        System.out.println("\n2. Getting User by ID (ID=2):");
        String user = controller.getUser(2);
        System.out.println("   Found user: " + user);
        
        // 3. Create new user
        System.out.println("\n3. Creating New User (Diana):");
        String createResult = controller.createUser("Diana");
        System.out.println("   Result: " + createResult);
        
        // 4. List users after creation
        System.out.println("\n4. Listing All Users After Creation:");
        users = controller.listUsers();
        for (String u : users) {
            System.out.println("   - " + u);
        }
        
        // 5. Delete user
        System.out.println("\n5. Deleting User (ID=1):");
        String deleteResult = controller.deleteUser(1);
        System.out.println("   Result: " + deleteResult);
        
        // 6. List users after deletion
        System.out.println("\n6. Listing All Users After Deletion:");
        users = controller.listUsers();
        for (String u : users) {
            System.out.println("   - " + u);
        }
        
        // 7. Test validation
        System.out.println("\n7. Testing Validation (Empty Username):");
        String validationResult = controller.createUser("");
        System.out.println("   Result: " + validationResult);
        
        System.out.println("\n========================================");
        System.out.println(" ALL USER CONTROLLER TESTS COMPLETED!");
        System.out.println("========================================");
    }
}
