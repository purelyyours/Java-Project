package ui;

import api.AdminResource;
import service.CustomerService;
import service.ReservationService;
import model.*;

import java.util.Scanner;

public class AdminMenu {

    private static final Scanner sc = new Scanner(System.in);

    public static void start() {
        boolean running = true;

        while (running) {
            System.out.println("""
--- ADMIN MENU ---
1. See all Customers
2. See all Rooms
3. See all Reservations
4. Add a Room
5. Back to Main Menu
""");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> showAllCustomers();
                case "2" -> showAllRooms();
                case "3" -> showAllReservations();
                case "4" -> addRoom();
                case "5" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void showAllCustomers() {
        CustomerService.getInstance()
                .getAllCustomers()
                .forEach(System.out::println);
    }

    private static void showAllRooms() {
        AdminResource.getAllRooms()
                .forEach(System.out::println);
    }

    private static void showAllReservations() {
        ReservationService.getInstance()
                .getAllReservations()
                .forEach(System.out::println);
    }

    private static void addRoom() {
        try {
            System.out.print("Room number: ");
            String number = sc.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Type (SINGLE/DOUBLE): ");
            RoomType type = RoomType.valueOf(sc.nextLine());

            AdminResource.addRoom(new Room(number, price, type));
            System.out.println("Room added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding room. Please try again.");
        }
    }
}
