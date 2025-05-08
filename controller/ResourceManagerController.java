package controller;
import entity.Room;
import entity.User;
import service.RoomService;

import java.util.Scanner;

public class ResourceManagerController {
    private final Scanner scanner = new Scanner(System.in);
    private final RoomService roomService = new RoomService();

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n== Resource Manager Menu ==");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. View All Rooms");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addRoom(); break;
                case "2": updateRoom(); break;
                case "3": deleteRoom(); break;
                case "4": viewRooms(); break;
                case "5": {
                    User user = new AuthController().showLoginMenu();

        new UserMenuController().loadMenu(user);
        running = false; break;}
                default: System.out.println("Invalid option.");
            }
        }
    }

private void addRoom() {
    String id;
    while (true) {
        System.out.print("Room ID (integer only): ");
        id = scanner.nextLine();
        if (id.matches("\\d+")) break;
        System.out.println(" Room ID must be an integer and a positive number. Please try again.");
    }

    String name;
    while (true) {
        System.out.print("Room Name (letters/numbers only): ");
        name = scanner.nextLine();
        if (name.matches("[a-zA-Z0-9 ]+")) break;
        System.out.println("Room Name must be alphabetic or alphanumeric.");
    }

    double cost;
    while (true) {
        System.out.print("Cost Per Hour: ");
        try {
            cost = Double.parseDouble(scanner.nextLine());
            if (cost <= 0) {
                System.out.println("Provide a valid cost (> 0).");
                continue;
            }
            break;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric cost.");
        }
    }

    try {
        roomService.addRoom(new Room(id, name, cost));
        System.out.println("Room added.");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}


private void updateRoom() {
    System.out.print("Enter Room ID to update: ");
    String id = scanner.nextLine();
    Room existing = roomService.getRoomById(id);
    if (existing == null) {
        System.out.println("Room not found.");
        return;
    }

    System.out.print("New Room Name: ");
    String name = scanner.nextLine();

    double cost;
    while (true) {
        System.out.print("New Cost Per Hour: ");
        try {
            cost = Double.parseDouble(scanner.nextLine());
            if (cost <= 0) {
                System.out.println("Provide a valid cost (> 0).");
                continue;
            }
            break;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric cost.");
        }
    }

    try {
        existing.setName(name);
        existing.setCostPerHour(cost);
        roomService.updateRoom(existing);
        System.out.println("✅ Room updated.");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

    private void deleteRoom() {
        System.out.print("Enter Room ID to delete: ");
       String id = scanner.nextLine();
        try {
            roomService.deleteRoom(id);
            System.out.println("Room deleted.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewRooms() {
        if(roomService.getAllRooms().isEmpty()){
            System.out.println("No rooms available.");
            return;
        }
    
        
        for (Room room : roomService.getAllRooms()) {
            System.out.println(room);
        }
    }
}
