package entity;

public class Booking {
    private Room room;
    private DateTimeRange timeRange;
    private double cost;
    private String username;

    public Booking(Room room, DateTimeRange timeRange, String username) {
        this.room = room;
        this.timeRange = timeRange;
        this.username = username;
        this.cost = room.getCostPerHour() * timeRange.getHours();
    }

    public Room getRoom() { return room; }
    public DateTimeRange getTimeRange() { return timeRange; }
    public double getCost() { return cost; }
    public String getUsername() { return username; }

    @Override
    public String toString() {
        return "Booking{room=" + room.getName() +
               ", timeRange=" + timeRange +
               ", cost=" + cost +
               ", user=" + username + "}";
    }
}
