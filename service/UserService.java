package service;

import entity.*;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepo = new UserRepository();

    public boolean register(String username, String password, String role) {
        if (userRepo.exists(username)) return false;

        User user;
        switch (role.toUpperCase()) {
            case "ADMIN":
                user = new Admin(username, password); break;
            case "RESOURCE_MANAGER":
                user = new ResourceManager(username, password); break;
            case "REGULAR_USER":
                user = new RegularUser(username, password); break;
            default:
                throw new IllegalArgumentException("Invalid role.");
        }

        userRepo.save(user);
        return true;
    }

    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
