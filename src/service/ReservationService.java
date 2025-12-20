package service;

import model.*;

import java.util.*;

public class ReservationService {

    private static final ReservationService INSTANCE =
            new ReservationService();

    private final Set<IRoom> rooms = new HashSet<>();
    private final Set<Reservation> reservations = new HashSet<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return INSTANCE;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public Set<IRoom> getRooms() {          // ✅ REQUIRED
        return rooms;
    }

    public Collection<IRoom> findAvailableRooms(
            Date checkIn, Date checkOut) {

        List<IRoom> available = new ArrayList<>();

        for (IRoom room : rooms) {
            boolean booked = false;

            for (Reservation r : reservations) {
                if (r.getRoom().equals(room)
                        && checkIn.before(r.getCheckOut())
                        && checkOut.after(r.getCheckIn())) {
                    booked = true;
                    break;
                }
            }

            if (!booked) available.add(room);
        }
        return available;
    }

    public void reserve(Customer customer, IRoom room,
                        Date checkIn, Date checkOut) {
        reservations.add(
                new Reservation(customer, room, checkIn, checkOut));
    }

    public Collection<Reservation> getCustomerReservations(
            Customer customer) {

        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCustomer().equals(customer)) {
                result.add(r);
            }
        }
        return result;
    }

    public Set<Reservation> getAllReservations() {
        return reservations;
    }
}
