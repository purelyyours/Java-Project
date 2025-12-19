import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationService {

    private static final ReservationService instance =
            new ReservationService();

    private final Set<Reservation> reservations = new HashSet<>();

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public Reservation reserveRoom(Customer customer, Room room,
                                   Date checkIn, Date checkOut) {

        if (isRoomBooked(room, checkIn, checkOut)) {
            throw new IllegalArgumentException("Room already booked");
        }

        Reservation reservation =
                new Reservation(customer, room, checkIn, checkOut);
        reservations.add(reservation);
        return reservation;
    }

    private boolean isRoomBooked(Room room, Date start, Date end) {
        for (Reservation r : reservations) {
            if (!r.getRoom().equals(room)) {
                continue;
            }

            boolean overlap =
                    start.before(r.getCheckOutDate()) &&
                            end.after(r.getCheckInDate());

            if (overlap) {
                return true;
            }
        }
        return false;
    }

    public Collection<Room> findAvailableRooms(
            Collection<Room> rooms,
            Date checkIn,
            Date checkOut) {

        List<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (!isRoomBooked(room, checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Collection<Room> findRecommendedRooms(
            Collection<Room> rooms,
            Date checkIn,
            Date checkOut) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(checkIn);
        calendar.add(Calendar.DATE, 7);
        Date newCheckIn = calendar.getTime();

        calendar.setTime(checkOut);
        calendar.add(Calendar.DATE, 7);
        Date newCheckOut = calendar.getTime();

        return findAvailableRooms(rooms, newCheckIn, newCheckOut);
    }

    public Collection<Reservation> getCustomerReservations(
            Customer customer) {

        List<Reservation> customerReservations = new ArrayList<>();

        for (Reservation r : reservations) {
            if (r.getCustomer().equals(customer)) {
                customerReservations.add(r);
            }
        }
        return customerReservations;
    }

    public Collection<Reservation> getAllReservations() {
        return reservations;
    }
}
