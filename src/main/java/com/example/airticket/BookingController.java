// BookingController.java
package com.example.airticket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    // inject both via constructor
    public BookingController(BookingService bookingService,
                             BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        // All possible seats (you can later load this from DB)
        List<String> allSeats = List.of("A1", "A2", "B1", "B2", "C1", "C2");

        // Booked seats from DB
        List<String> bookedSeats = bookingRepository.findAll().stream()
                .map(Flight::getSeat)
                .collect(Collectors.toList());

        // Available seats = all - booked
        List<String> availableSeats = allSeats.stream()
                .filter(seat -> !bookedSeats.contains(seat))
                .toList();

        model.addAttribute("seats", availableSeats);
        model.addAttribute("request", new BookingRequest());
        model.addAttribute("message", "Choose a seat and book your flight");

        return "index";
    }

    @PostMapping("/book")
    public String book(@ModelAttribute BookingRequest request, Model model) {
        try {
            Flight booking = bookingService.bookSeat(
                    request.getSeat(), request.getPassengerName()
            );

            // UNIQUE CODE IS HERE:
            String bookingCode = booking.getBookingId();

            model.addAttribute("message",
                    "Booking confirmed for " + booking.getPassengerName() +
                    " on seat " + booking.getSeat() +
                    ". Code: " + bookingCode +
                    ". Total: $" + booking.getPrice()
            );

            return "confirmation";
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return home(model);  // reload page with error
        }
    }
}
