package api;

import model.*;
import service.*;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static final CustomerService cs =
            CustomerService.getInstance();
    private static final ReservationService rs =
            ReservationService.getInstance();

    public static void createCustomer(
            String first, String last, String email) {
        cs.addCustomer(new Customer(first, last, email));
    }

    public static Collection<IRoom> findRooms(
            Date in, Date out) {
        return rs.findAvailableRooms(in, out);
    }

    public static void bookRoom(
            String email, IRoom room,
            Date in, Date out) {

        Customer c = cs.getCustomer(email);
        if (c == null)
            throw new IllegalArgumentException("Customer not found");

        rs.reserve(c, room, in, out);
    }

    public static Collection<Reservation> getCustomerReservations(
            String email) {

        Customer c = cs.getCustomer(email);
        if (c == null)
            throw new IllegalArgumentException("Customer not found");

        return rs.getCustomerReservations(c);
    }
}
