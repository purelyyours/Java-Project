import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RoomService {

    private static final RoomService instance = new RoomService();

    private final Set<Room> rooms = new HashSet<>();

    private RoomService() {
    }

    public static RoomService getInstance() {
        return instance;
    }

    public void addRoom(String roomNumber, double price, RoomType roomType) {
        rooms.add(new Room(roomNumber, price, roomType));
    }

    public Collection<Room> getAllRooms() {
        return rooms;
    }
}
