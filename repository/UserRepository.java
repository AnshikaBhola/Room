package repository;

import entity.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public void save(User user) {
        userMap.put(user.getUsername(), user);
    }

    public User findByUsername(String username) {
        return userMap.get(username);
    }

    public boolean exists(String username) {
        return userMap.containsKey(username);
    }
}
