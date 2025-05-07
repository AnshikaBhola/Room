package repository;

import entity.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    public void save(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> findAll() {
        return bookings;
    }

    public void delete(Booking booking) {
        bookings.remove(booking);
    }
}

