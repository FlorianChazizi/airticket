package com.example.airticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Flight { 
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String bookingId;

    @Column(nullable = false, unique = true)
    private String seat;

    @Column(nullable = false)
    private String passengerName;   

    @Column(nullable = false)
    private int price = 150;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    public Flight() {}
      
    public Flight(String seat, String passengerName) {
        this.seat = seat;
        this.passengerName = passengerName;
        this.bookingId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.bookingTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }
    public String getBookingId() {
        return bookingId;
    }
    public String getSeat() {
        return seat;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public int getPrice() {
        return price;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }    
}
