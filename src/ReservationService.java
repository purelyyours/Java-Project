import java.util.*;

public class ReservationService {

    private final Set<Reservation> reservations = new HashSet<>();

    public Reservation reserveRoom(Customer customer, Room room,
                                   Date checkIn, Date checkOut) {

        if (hasConflict(room, checkIn, checkOut)) {
            throw new IllegalArgumentException("Room already booked");
        }

        Reservation reservation =
                new Reservation(customer, room, checkIn, checkOut);
        reservations.add(reservation);
        return reservation;
    }

    private boolean hasConflict(Room room, Date start, Date end) {
        for (Reservation r : reservations) {
            if (!r.getRoom().equals(room)) continue;

            boolean overlap =
                    start.before(r.getCheckOutDate()) &&
                            end.after(r.getCheckInDate());

            if (overlap) return true;
        }
        return false;
    }

    public Collection<Room> findAvailableRooms(
            Collection<Room> rooms, Date start, Date end) {

        List<Room> available = new ArrayList<>();
        for (Room r : rooms) {
            if (!hasConflict(r, start, end)) {
                available.add(r);
            }
        }
        return available;
    }

    public Collection<Reservation> getCustomerReservations(Customer customer) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCustomer().equals(customer)) {
                list.add(r);
            }
        }
        return list;
    }

    public Collection<Reservation> getAllReservations() {
        return reservations;
    }
}
