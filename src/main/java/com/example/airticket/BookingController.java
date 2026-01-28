package com.example.airticket;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {
    private Flight flight = new Flight();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("seats", flight.getAvailableSeats());
        model.addAttribute("request", new BookingRequest());
        model.addAttribute("message", "Choose a seat and book your flight 😊");
        return "index";
    }

    @PostMapping("/book")
    public String book(@ModelAttribute BookingRequest request, Model model) {
        String result = flight.bookSeat(request.getSeat(),
                request.getPassengerName());
                model.addAttribute("message", result);
                model.addAttribute("seat", flight.getAvailableSeats());
                model.addAttribute("request", new BookingRequest());
                return "confirmation";
    }
}
