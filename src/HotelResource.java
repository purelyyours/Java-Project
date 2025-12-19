import java.util.*;

public class HotelResource {

    private static final CustomerService customerService = new CustomerService();
    private static final RoomService roomService = new RoomService();
    private static final ReservationService reservationService =
            new ReservationService();

    public static void createCustomer(String first, String last, String email) {
        customerService.addCustomer(first, last, email);
    }

    public static Collection<Room> findRoom(Date checkIn, Date checkOut) {
        return reservationService.findAvailableRooms(
                roomService.getAllRooms(), checkIn, checkOut);
    }

    public static Reservation bookRoom(String email, Room room,
                                       Date checkIn, Date checkOut) {
        Customer customer = customerService.getCustomer(email);
        return reservationService.reserveRoom(
                customer, room, checkIn, checkOut);
    }

    public static void addRoom(String roomNumber, double price, RoomType type) {
        roomService.addRoom(roomNumber, price, type);
    }

    public static Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public static Collection<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    public static Collection<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    public static Collection<Reservation> getCustomerReservations(String email) {
        Customer customer = customerService.getCustomer(email);
        return reservationService.getCustomerReservations(customer);
    }
}
