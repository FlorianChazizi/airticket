package com.example.airticket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Flight {
    private String id = "FL001";
    private List<String> availableSeats = new ArrayList<>(List.of("A1", "A2", "B1", "B2", "C1", "C2"));
    private int price = 150;

    public String bookSeat(String seat, String passenger) {
        if (availableSeats.remove(seat)) {
            String bookingId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            return "Booking confirmed for " + passenger + " on seat " + seat + ". ID: " + bookingId + ". Total: $"
                    + price;
        }
        return "Seat " + seat + " unavailable.";
    }

    public List<String> getAvailableSeats() {
        return availableSeats;
    }
}
