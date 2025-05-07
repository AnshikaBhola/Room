package entity;

public class ResourceManager extends User {
    public ResourceManager(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "RESOURCE_MANAGER";
    }
}
