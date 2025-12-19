import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf =
            new SimpleDateFormat("dd/MM/yyyy");

    public static void start() {
        while (true) {
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin ");
            System.out.println("5. Exit");

            try {
                switch (scanner.nextLine()) {
                    case "1" -> searchRooms();
                    case "2" -> viewReservations();
                    case "3" -> createCustomer();
                    case "4" -> AdminMenu.start();
                    case "5" -> { return; }
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void searchRooms() throws Exception {
        System.out.print("Check-in date (dd/MM/yyyy): ");
        Date in = sdf.parse(scanner.nextLine());
        System.out.print("Check-out date (dd/MM/yyyy): ");
        Date out = sdf.parse(scanner.nextLine());

        HotelResource.findRoom(in, out)
                .forEach(System.out::println);
    }

    private static void viewReservations() {
        System.out.print("Email: ");
        HotelResource.getCustomerReservations(scanner.nextLine())
                .forEach(System.out::println);
    }

    private static void createCustomer() {
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
