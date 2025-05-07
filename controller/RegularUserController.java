package controller;

import entity.Booking;
import entity.DateTimeRange;
import entity.Room;
import entity.User;
import service.BookingService;
import service.RoomService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class RegularUserController {
    private final Scanner scanner = new Scanner(System.in);
    private final RoomService roomService = new RoomService();
    private final BookingService bookingService = new BookingService();

    public void showMenu(String username) {
        boolean running = true;
        while (running) {
            System.out.println("\n== Regular User Menu ==");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": viewRooms(); break;
                case "2": bookRoom(username); break;
                case "3": viewBookings(username); break;
                case "4": cancelBooking(username); break;
                case "5": {
                   User user= new AuthController().showLoginMenu();
                    new UserMenuController().loadMenu(user);
                    running = false; break;
                }
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void viewRooms() {
        if(roomService.getAllRooms().isEmpty()){
            System.out.println("No rooms available.");
        } else {
            
        for (Room r : roomService.getAllRooms()) {
            System.out.println(r);
        }
       }
    }

    private void bookRoom(String username) {
        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine();
        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        try {
            System.out.print("Enter start time (yyyy-MM-ddTHH:mm): ");
            LocalDateTime start = LocalDateTime.parse(scanner.nextLine());
            System.out.print("Enter end time (yyyy-MM-ddTHH:mm): ");
            LocalDateTime end = LocalDateTime.parse(scanner.nextLine());
            DateTimeRange range = new DateTimeRange(start, end);

            Booking booking = bookingService.bookRoom(room, range, username);
            System.out.println("Booking confirmed: " + booking);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewBookings(String username) {
        List<Booking> bookings = bookingService.getUserBookings(username);
        if (bookings.isEmpty()) {
            System.out.println("You have no bookings.");
        } else {
            for (int i = 0; i < bookings.size(); i++) {
                System.out.println((i + 1) + ". " + bookings.get(i));
            }
        }
    }

    private void cancelBooking(String username) {
        List<Booking> bookings = bookingService.getUserBookings(username);
        if (bookings.isEmpty()) {
            System.out.println("No bookings to cancel.");
            return;
        }

        viewBookings(username);
        System.out.print("Enter booking number to cancel: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < bookings.size()) {
            bookingService.cancelBooking(bookings.get(index));
            System.out.println("Booking cancelled.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
