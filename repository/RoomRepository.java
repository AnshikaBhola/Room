package repository;

import entity.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private static final String FILE_NAME = "rooms.txt";
    private List<Room> rooms;

    public RoomRepository() {
        this.rooms = loadRooms();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        saveToFile();
    }

    public void updateRoom(String roomId, String newName, double newCost) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                room.setName(newName);
                room.setCostPerHour(newCost);
                saveToFile();
                return;
            }
        }
    }

    public void deleteRoom(String roomId) {
        rooms.removeIf(room -> room.getRoomId().equals(roomId));
        saveToFile();
    }

    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    public boolean exists(String roomId) {
        return rooms.stream().anyMatch(room -> room.getRoomId().equals(roomId));
    }

    public Room findById(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Room room : rooms) {
                writer.write(room.toDataString());  // <- we'll define this method in Room
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save room data: " + e.getMessage());
        }
    }

    private List<Room> loadRooms() {
        List<Room> loadedRooms = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) return loadedRooms;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                try {
                    Room room = Room.fromString(line);
                    loadedRooms.add(room);
                } catch (Exception e) {
                    System.out.println("Skipping invalid room entry: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load room data: " + e.getMessage());
        }

        return loadedRooms;
    }
}
