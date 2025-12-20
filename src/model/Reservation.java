package model;

import java.util.Date;

public class Reservation {

    private final Customer customer;
    private final IRoom room;
    private final Date checkIn;
    private final Date checkOut;

    public Reservation(Customer customer, IRoom room,
                       Date checkIn, Date checkOut) {
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Customer getCustomer() { return customer; }
    public IRoom getRoom() { return room; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }

    @Override
    public String toString() {
        return customer + " reserved " + room +
                " from " + checkIn + " to " + checkOut;
    }
}
