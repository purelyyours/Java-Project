import java.util.*;

public class RoomService {

    private final Set<Room> rooms = new HashSet<>();

    public void addRoom(String roomNumber, double price, RoomType type) {
        rooms.add(new Room(roomNumber, price, type));
    }

    public Collection<Room> getAllRooms() {
        return rooms;
    }
}
