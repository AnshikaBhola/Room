package service;

import entity.Room;
import repository.RoomRepository;

import java.util.Collection;

public class RoomService {
    private final RoomRepository repo = new RoomRepository();

    public void addRoom(Room room) {
        if (repo.exists(room.getRoomId())) {
            throw new IllegalArgumentException("Room ID already exists.");
        }
        repo.addRoom(room);
    }

    public void updateRoom(Room room) {
        if (!repo.exists(room.getRoomId())) {
            throw new IllegalArgumentException("Room ID does not exist.");
        }
        repo.deleteRoom(room.getRoomId());
        repo.addRoom(room); // overwrite
    }

    public void deleteRoom(String id) {
        if (!repo.exists(id)) {
            throw new IllegalArgumentException("Room not found.");
        }
        repo.deleteRoom(id);
    }

    public Room getRoomById(String id) {
        return repo.findById(id);
    }

    public Collection<Room> getAllRooms() {
        return repo.findAll();
    }
}
