package service;

import entity.Booking;
import entity.DateTimeRange;
import entity.Room;
import repository.BookingRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingRepository repo = new BookingRepository();

    public boolean isAvailable(Room room, DateTimeRange range) {
        return repo.findAll().stream()
                .filter(b -> b.getRoom().getRoomId()==(room.getRoomId()))
                .noneMatch(b -> b.getTimeRange().overlaps(range));
    }

    public Booking bookRoom(Room room, DateTimeRange range, String username) {
        if (!isAvailable(room, range)) {
            throw new IllegalArgumentException("Room is not available for this time slot.");
        }
        Booking booking = new Booking(room, range, username);
        repo.save(booking);
        return booking;
    }

    public List<Booking> getUserBookings(String username) {
        return repo.findAll().stream()
                .filter(b -> b.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBookings() {
        return repo.findAll();
    }

    public void cancelBooking(Booking booking) {
        repo.delete(booking);
    }
}
