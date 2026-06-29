import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cognizant.service.UserRepository;
import com.cognizant.service.UserService;

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

public class UserServiceDemo {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  USER SERVICE DEMO - Exercise 2");
        System.out.println("========================================\n");
        
        // Setup
        UserRepository repository = new MockUserRepository();
        UserService service = new UserService(repository);
        
        // 1. Get user count
        System.out.println("1. Total User Count:");
        int count = service.getUserCount();
        System.out.println("   Count: " + count);
        
        // 2. List all users
        System.out.println("\n2. Listing All Users:");
        List<String> users = service.getAllUsers();
        for (String user : users) {
            System.out.println("   - " + user);
        }
        
        // 3. Get specific user
        System.out.println("\n3. Getting User by ID (ID=2):");
        String user = service.getUserById(2);
        System.out.println("   Found user: " + user);
        
        // 4. Save new user
        System.out.println("\n4. Saving New User (Diana):");
        boolean saveResult = service.saveUser("Diana");
        System.out.println("   Result: " + (saveResult ? "Success" : "Failed"));
        
        // 5. List users after saving
        System.out.println("\n5. Listing All Users After Save:");
        users = service.getAllUsers();
        for (String u : users) {
            System.out.println("   - " + u);
        }
        System.out.println("   New Count: " + service.getUserCount());
        
        // 6. Delete user
        System.out.println("\n6. Deleting User (ID=1):");
        boolean deleteResult = service.deleteUser(1);
        System.out.println("   Result: " + (deleteResult ? "Success" : "Failed"));
        
        // 7. List users after deletion
        System.out.println("\n7. Listing All Users After Deletion:");
        users = service.getAllUsers();
        for (String u : users) {
            System.out.println("   - " + u);
        }
        System.out.println("   New Count: " + service.getUserCount());
        
        // 8. Test validation
        System.out.println("\n8. Testing Validation (Empty Username):");
        boolean validationResult = service.saveUser("");
        System.out.println("   Result: " + (validationResult ? "Success" : "Failed - Validation Worked!"));
        
        System.out.println("\n========================================");
        System.out.println("  ALL USER SERVICE TESTS COMPLETED!");
        System.out.println("========================================");
    }
}
