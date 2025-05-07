package entity;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomId;
    private String name;
    private double costPerHour;

    public Room(String roomId, String name, double costPerHour) {
        this.roomId = roomId;
        this.name = name;
        this.costPerHour = costPerHour;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    @Override
    public String toString() {
        return roomId + "," + name + "," + costPerHour;
    }

    public static Room fromString(String line) {
        String[] parts = line.split(",");
        return new Room(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}
