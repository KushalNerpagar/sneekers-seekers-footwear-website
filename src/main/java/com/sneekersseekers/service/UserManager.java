package com.sneekersseekers.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserManager {
    
    private static final Map<String, String> users = new HashMap<>();
    
    static {
        users.put("admin", "password123");
        users.put("user1", "test123");
        users.put("john", "john123");
        users.put("sarah", "sarah456");
    }
    
    public boolean registerUser(String username, String password) {
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }
        
        if (users.containsKey(username)) {
            return false; 
        }
        
        users.put(username, password);
        return true;
    }
    
    public boolean authenticate(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
    
    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}
