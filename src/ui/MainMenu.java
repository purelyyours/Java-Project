package ui;

import api.HotelResource;
import model.IRoom;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleDateFormat df =
            new SimpleDateFormat("dd/MM/yyyy");

    public static void start() {
        while (true) {
            System.out.println("""
                                --- HOTEL MENU ---
                                1. Find and reserve a room
                                2. See my reservations
                                3. Create an account
                                4. Admin
                                5. Exit
""");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> findAndReserveRoom();
                case "2" -> seeReservations();
                case "3" -> createAccount();
                case "4" -> AdminMenu.start();
                case "5" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void findAndReserveRoom() {
        try {
            System.out.print("Check-in: ");
            Date checkIn = df.parse(sc.nextLine());

            System.out.print("Check-out: ");
            Date checkOut = df.parse(sc.nextLine());

            Collection<IRoom> rooms =
                    HotelResource.findRooms(checkIn, checkOut);

            if (rooms.isEmpty()) {
                System.out.println("No rooms available for given dates.");
                return;
            }

            rooms.forEach(System.out::println);

            System.out.print("Would you like to book a room? (yes/no): ");
            String answer = sc.nextLine();

            if (!answer.equalsIgnoreCase("yes")) {
                return;
            }

            System.out.print("Enter room number: ");
            String roomNumber = sc.nextLine();

            System.out.print("Enter your email: ");
            String email = sc.nextLine();

            for (IRoom room : rooms) {
                if (room.getRoomNumber().equals(roomNumber)) {
                    try {
                        HotelResource.bookRoom(
                                email, room, checkIn, checkOut);
                        System.out.println("Room reserved successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Please create an account first.");
                    }
                    return;
                }
            }

            System.out.println("Invalid room number.");

        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static void seeReservations() {
        try {
            System.out.print("Email: ");
            String email = sc.nextLine();

            HotelResource.getCustomerReservations(email)
                    .forEach(System.out::println);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createAccount() {
        try {
            System.out.print("First name: ");
            String firstName = sc.nextLine();

            System.out.print("Last name: ");
            String lastName = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            HotelResource.createCustomer(
                    firstName, lastName, email);

            System.out.println("Account created.");

        } catch (Exception e) {
            System.out.println("Unable to create account.");
        }
    }
}
