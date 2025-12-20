import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static final CustomerService customerService =
            CustomerService.getInstance();
    private static final RoomService roomService =
            RoomService.getInstance();
    private static final ReservationService reservationService =
            ReservationService.getInstance();

    public static void createCustomer(
            String firstName, String lastName, String email) {

        customerService.addCustomer(firstName, lastName, email);
    }

    public static Collection<Room> findRoom(
            Date checkIn, Date checkOut) {

        return reservationService.findAvailableRooms(
                roomService.getAllRooms(), checkIn, checkOut);
    }

    public static Collection<Room> findRecommendedRooms(
            Date checkIn, Date checkOut) {

        return reservationService.findRecommendedRooms(
                roomService.getAllRooms(), checkIn, checkOut);
    }

    public static Reservation bookRoom(
            String email, Room room,
            Date checkIn, Date checkOut) {

        Customer customer = customerService.getCustomer(email);

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer not found. Please create an account first.");
        }

        return reservationService.reserveRoom(
                customer, room, checkIn, checkOut);
    }


    public static void addRoom(
            String roomNumber, double price, RoomType roomType) {

        roomService.addRoom(roomNumber, price, roomType);
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

    public static Collection<Reservation> getCustomerReservations(
            String email) {

        Customer customer = customerService.getCustomer(email);
        return reservationService.getCustomerReservations(customer);
    }
}
