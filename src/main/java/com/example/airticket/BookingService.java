package com.example.airticket;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
     public Flight bookSeat(String seat, String passengerName) {
        // 1. Check if seat already booked
        bookingRepository.findBySeat(seat).ifPresent(b -> {
            throw new IllegalStateException("Seat " + seat + " is already booked.");
        });

        // 2. Create new booking with unique bookingId
        Flight booking = new Flight(seat, passengerName);

        // 3. Save to DB
        return bookingRepository.save(booking);
    }
}

