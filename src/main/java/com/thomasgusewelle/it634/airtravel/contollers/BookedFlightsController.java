package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.BookedFlights;
import com.thomasgusewelle.it634.airtravel.models.Flight;
import com.thomasgusewelle.it634.airtravel.repositories.BookedFlightRepository;
import com.thomasgusewelle.it634.airtravel.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookedFlightsController {
    @Autowired
    private BookedFlightRepository bookedFlightRepository;
    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/bookFlight/{id}")
    public String PostBookFlight(@PathVariable String id) {
        BookedFlights bookedFlight = new BookedFlights();
//        Get logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
//        find flight
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isEmpty()) {
            return "redirect:/";
        }
        bookedFlight.setFlight(flight.get());
        bookedFlight.setUserId(userId);
        bookedFlightRepository.save(bookedFlight);
        return "redirect:/bookedFlights";
    }

    @GetMapping("/bookedFlights")
    public String GetBookedFlights(Model model) {
//        Get logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
//        Get list of booked flights by the user's email
        List<BookedFlights> bookedFlights = bookedFlightRepository.findBookedFlightsByUserId(userId.toLowerCase());
        model.addAttribute("flights", bookedFlights);

        return "/flightSearch/bookedFlights";
    }
}
