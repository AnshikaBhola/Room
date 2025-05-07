import controller.AuthController;
import controller.UserMenuController;
import entity.User;

public class Main {
    public static void main(String[] args) {
        AuthController authController = new AuthController();
        User user = authController.showLoginMenu();

        new UserMenuController().loadMenu(user);
        
    }
}