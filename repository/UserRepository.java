package repository;

import entity.Admin;
import entity.RegularUser;
import entity.ResourceManager;
import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE_NAME = "users.txt";
    private List<User> users;

    public UserRepository() {
        users = loadUsers();

        
        if (findByUsername("admin") == null) {
            users.add(new Admin("admin", "admin123"));

            saveUsers(); 
        }
    }

   
    public void addUser(User user) {
        if (!exists(user.getUsername())) {
            users.add(user);
            saveUsers();
        }
    }

    
    public boolean exists(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    public boolean validateUser(String username, String password) {
        return users.stream().anyMatch(u ->
                u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    public String getUserRole(String username) {
        User user = findByUsername(username);
        return (user != null) ? user.getRole() : null;
    }

   
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                if (!user.getUsername().equals("admin")) {
                    writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
   private List<User> loadUsers() {
    List<User> list = new ArrayList<>();
    File file = new File(FILE_NAME);
    if (!file.exists()) return list;

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String username = parts[0];
                String password = parts[1];
                String role = parts[2];

                switch (role) {
                    case "ADMIN":
                        list.add(new Admin(username, password));
                        break;
                    case "RESOURCE_MANAGER":
                        list.add(new ResourceManager(username, password));
                        break;
                    case "REGULAR_USER":
                        list.add(new RegularUser(username, password));
                        break;
                    default:
                        System.out.println("Unknown role for user: " + username);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return list;
}

}
