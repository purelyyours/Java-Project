import java.util.Objects;

public class Room {

    private final String roomNumber;
    private final double pricePerNight;
    private final RoomType roomType;

    public Room(String roomNumber, double pricePerNight, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Room)) return false;
        Room other = (Room) obj;
        return roomNumber.equals(other.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return "Room " + roomNumber +
                " [" + roomType + "] " +
                (pricePerNight == 0 ? "Free" : "$" + pricePerNight);
    }
}
