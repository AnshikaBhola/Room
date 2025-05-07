package repository;

import entity.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private static final String FILE_NAME = "rooms.txt";
    private List<Room> rooms;

    public RoomRepository() {
        rooms = loadRooms();
    }

    // Save (add new room)
    public void addRoom(Room room) {
        rooms.add(room);
        saveToFile();
    }

    // Update room
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

    // Delete room
    public void deleteRoom(String roomId) {
        rooms.removeIf(room -> room.getRoomId().equals(roomId));
        saveToFile();
    }

    // Get all rooms
    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    // Check if room exists
    public boolean exists(String roomId) {
        return rooms.stream().anyMatch(room -> room.getRoomId().equals(roomId));
    }

    // Find room by ID
    public Room findById(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    // Save to file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Room room : rooms) {
                writer.write(room.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load from file
    private List<Room> loadRooms() {
        List<Room> roomList = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return roomList;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                roomList.add(Room.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return roomList;
    }
}
