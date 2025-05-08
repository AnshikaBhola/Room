package controller;

import entity.User;

public class UserMenuController {
    public void loadMenu(User user) {
        if (user == null) {
            System.out.println("Error: User object is null.");
            return;
        }

        switch (user.getRole()) {
            case "ADMIN":
                new ResourceManagerController().showMenu();
                break;
            case "RESOURCE_MANAGER":
                new ResourceManagerController().showMenu();
                break;
            case "REGULAR_USER":
                new RegularUserController().showMenu(user.getUsername());
                break;
            default:
                System.out.println("Unknown role.");
        }
    }
}
