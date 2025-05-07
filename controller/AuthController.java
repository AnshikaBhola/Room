package controller;

import entity.User;
import service.UserService;

import java.util.Scanner;

public class AuthController {
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);

    public User showLoginMenu() {
        while (true) {
            System.out.println("\n=== Welcome to SRBMS ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": registerUser(); break;
                case "2": {
                    User user = loginUser();
                    if (user != null) return user;
                    break;
                }
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Username: ");
        String uname = scanner.nextLine();
        System.out.print("Password: ");
        String pwd = scanner.nextLine();
        System.out.print("Role (ADMIN / RESOURCE_MANAGER / REGULAR_USER): ");
        String role = scanner.nextLine();

        if (userService.register(uname, pwd, role)) {
            System.out.println("✅ Registered successfully.");
        } else {
            System.out.println("❌ Username already exists.");
        }
    }

    private User loginUser() {
        System.out.print("Username: ");
        String uname = scanner.nextLine();
        System.out.print("Password: ");
        String pwd = scanner.nextLine();

        User user = userService.login(uname, pwd);
        if (user != null) {
            System.out.println("✅ Login successful! Logged in as " + user.getRole());
            return user;
        } else {
            System.out.println("❌ Invalid credentials.");
            return null;
        }
    }
}
