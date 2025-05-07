package controller;

import entity.User;

public class UserMenuController {
    public void loadMenu(User user) {
        switch (user.getRole()) {
            case "ADMIN":
                System.out.println("🔧 Admin Menu: Coming soon...");

                ResourceManagerController rmc=new ResourceManagerController();
                rmc.showMenu();
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
