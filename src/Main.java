import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class HotelRoom {
    private String roomId;
    private String roomType;
    private int beds;
    private double pricePerNight;
    private boolean availability;

    public HotelRoom(String roomId, String roomType, int beds, double pricePerNight) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.availability = true;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public Customer(String customerId, String firstName, String lastName, String contactNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}

class Reservation {
    private String reservationId;
    private Customer customer;
    private HotelRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(String reservationId, Customer customer, HotelRoom room, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public HotelRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
}

class Hotel {
    public Map<String, HotelRoom> rooms;
    Map<String, Customer> customers;
    private Map<String, Reservation> reservations;

    public Hotel() {
        rooms = new HashMap<>();
        customers = new HashMap<>();
        reservations = new HashMap<>();
    }

    public void addRoom(HotelRoom room) {
        rooms.put(room.getRoomId(), room);
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public void makeReservation(Customer customer, HotelRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(generateReservationId(), customer, room, checkInDate, checkOutDate);
        reservations.put(reservation.getReservationId(), reservation);
        room.setAvailability(false);
    }

    private String generateReservationId() {
        return "RES-" + System.currentTimeMillis();
    }
}

