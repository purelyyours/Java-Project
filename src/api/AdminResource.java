package api;

import model.IRoom;
import service.ReservationService;

import java.util.Set;

public class AdminResource {

    private static final ReservationService rs =
            ReservationService.getInstance();

    public static void addRoom(IRoom room) {
        rs.addRoom(room);
    }

    public static Set<IRoom> getAllRooms() {
        return rs.getRooms();
    }
}
