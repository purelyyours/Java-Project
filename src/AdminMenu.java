import java.util.Scanner;

public class AdminMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Back");

            switch (scanner.nextLine()) {
                case "1" -> HotelResource.getAllCustomers()
                        .forEach(System.out::println);
                case "2" -> HotelResource.getAllRooms()
                        .forEach(System.out::println);
                case "3" -> HotelResource.getAllReservations()
                        .forEach(System.out::println);
                case "4" -> addRoom();
                case "5" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void addRoom() {
        System.out.print("Room number: ");
        String number = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Room type (SINGLE/DOUBLE): ");
        RoomType type =
                RoomType.valueOf(scanner.nextLine().toUpperCase());

        HotelResource.addRoom(number, price, type);
        System.out.println("Room added.");
    }
}
