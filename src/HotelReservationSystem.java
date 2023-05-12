import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        // Adding rooms to the hotel
        System.out.println("Adding Rooms to the Hotel");
        while (true) {
            System.out.print("Enter Room ID (or 'q' to quit): ");
            String roomId = scanner.nextLine();

            if (roomId.equalsIgnoreCase("q")) {
                break;
            }

            System.out.print("Enter Room Type: ");
            String roomType = scanner.nextLine();
            System.out.print("Enter Number of Beds: ");
            int beds = scanner.nextInt();
            System.out.print("Enter Price per Night: ");
            double pricePerNight = scanner.nextDouble();
            scanner.nextLine(); // Clear the input buffer

            HotelRoom room = new HotelRoom(roomId, roomType, beds, pricePerNight);
            hotel.addRoom(room);
        }

        // Adding customers
        System.out.println("\nAdding Customers");
        while (true) {
            System.out.print("Enter Customer ID (or 'q' to quit): ");
            String customerId = scanner.nextLine();

            if (customerId.equalsIgnoreCase("q")) {
                break;
            }

            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter Contact Number: ");
            String contactNumber = scanner.nextLine();

            Customer customer = new Customer(customerId, firstName, lastName, contactNumber);
            hotel.addCustomer(customer);
        }

        // Making a reservation
        System.out.println("\nMaking a Reservation");
        System.out.print("Enter Customer ID: ");
        String reservationCustomerId = scanner.nextLine();
        System.out.print("Enter Room ID: ");
        String reservationRoomId = scanner.nextLine();
        System.out.print("Enter Check-in Date (yyyy-MM-dd): ");
        String checkInDateString = scanner.nextLine();
        System.out.print("Enter Check-out Date (yyyy-MM-dd): ");
        String checkOutDateString = scanner.nextLine();

        // Parse dates
        Date checkInDate = parseDate(checkInDateString);
        Date checkOutDate = parseDate(checkOutDateString);

        if (checkInDate != null && checkOutDate != null) {
            Customer customer = hotel.customers.get(reservationCustomerId);
            HotelRoom room = hotel.rooms.get(reservationRoomId);

            if (customer != null && room != null && room.isAvailable()) {
                hotel.makeReservation(customer, room, checkInDate, checkOutDate);
                System.out.println("Reservation successfully made.");
            } else {
                System.out.println("Invalid Customer ID, Room ID, or Room not available. Reservation not made.");
            }
        } else {
            System.out.println("Invalid date format. Reservation not made.");
        }

        // Other operations on reservations, rooms, and customers

        scanner.close();
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
