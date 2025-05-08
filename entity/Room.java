package entity;

public class Room {
    
    private String RoomId;
    private String name;
    private double costPerHour;

    public Room(String RoomId, String name, double costPerHour) {
        this.RoomId = RoomId;
        this.name = name;
        this.costPerHour = costPerHour;
    }

    public static Room fromString(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Cannot parse empty room line.");
        }

        String[] parts = line.split(",");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid room format (expected 3 values): " + line);
        }

        String id = parts[0].trim();
        String name = parts[1].trim();
        double cost = Double.parseDouble(parts[2].trim());
        return new Room(id, name, cost);
    }

    public String getRoomId() { return RoomId; }
    public String getName() { return name; }
    public double getCostPerHour() { return costPerHour; }

    public void setName(String name) { this.name = name; }
    public void setCostPerHour(double costPerHour) { this.costPerHour = costPerHour; }

    @Override
    public String toString() {
        return "Room{" + "ID='" + RoomId + "', Name='" + name + "', Cost/hr=" + costPerHour + "}";
    }
    public String toDataString() {
    return RoomId + "," + name + "," + costPerHour;
}

}
