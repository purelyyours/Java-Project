import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf =
            new SimpleDateFormat("dd/MM/yyyy");

    public static void start() {
        while (true) {
            System.out.println("\n--- HOTEL MENU ---");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. see my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin ");
            System.out.println("5. Exit");

            try {
                switch (scanner.nextLine()) {
                    case "1" -> findRooms();
                    case "2" -> seeReservations();
                    case "3" -> createAccount();
                    case "4" -> AdminMenu.start();
                    case "5" -> {
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void findRooms() throws Exception {
        System.out.print("Check-in date (dd/MM/yyyy): ");
        Date checkIn = sdf.parse(scanner.nextLine());

        System.out.print("Check-out date (dd/MM/yyyy): ");
        Date checkOut = sdf.parse(scanner.nextLine());

        Collection<Room> rooms =
                HotelResource.findRoom(checkIn, checkOut);

        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            System.out.println("Recommended rooms:");
            HotelResource.findRecommendedRooms(checkIn, checkOut)
                    .forEach(System.out::println);
            return;
        }

        System.out.println("Available rooms:");
        rooms.forEach(System.out::println);

        System.out.print("Enter room number to book: ");
        String roomNumber = scanner.nextLine();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room selection.");
            return;
        }

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        HotelResource.bookRoom(email, selectedRoom, checkIn, checkOut);
        System.out.println("Room successfully booked!");
    }


    private static void seeReservations() {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Collection<Reservation> reservations =
                HotelResource.getCustomerReservations(email);

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }


    private static void createAccount() {
        System.out.print("First name: ");
        String first = scanner.nextLine();

        System.out.print("Last name: ");
        String last = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        HotelResource.createCustomer(first, last, email);
        System.out.println("Account created.");
    }
}
