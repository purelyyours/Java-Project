package model;

import java.util.Objects;

public class Room implements IRoom {

    private final String roomNumber;
    private final double price;
    private final RoomType roomType;

    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public String getRoomNumber() { return roomNumber; }
    public double getRoomPrice() { return price; }
    public RoomType getRoomType() { return roomType; }
    public boolean isFree() { return price == 0; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + roomType + "] " +
                (isFree() ? "Free" : "$" + price);
    }
}
