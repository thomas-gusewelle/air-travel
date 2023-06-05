package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.models.Flight;
import com.thomasgusewelle.it634.airtravel.repositories.AirportRepository;
import com.thomasgusewelle.it634.airtravel.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepo;

    //    Gets all flights from the repo and adds them to model
    @GetMapping("/allFlights")
    public String allFlightsPage(Model model) {
        List<Flight> flights = flightRepository.findAll();
        model.addAttribute("flights", flights);
        return "/manage/allFlights";
    }

    //    Creates new flight model and gives it to the template
    @GetMapping("/addFlight")
    public String addFlightPage(Model model) {
        List<Airport> airports = airportRepo.findAll();
        model.addAttribute("airports", airports);
        model.addAttribute("newFlight", new Flight());
        return "/manage/addFlight";
    }

    //    Post mapping for adding a flight
    @PostMapping("/addFlight")
    public String addFlight(@ModelAttribute("newFlight") Flight newFlight) {
        flightRepository.save(newFlight);
        return "redirect:/allFlights";
    }

    @GetMapping("/flight/edit/{id}")
    public String getEditFlight(Model model, @PathVariable(name = "id") String id) {
        List<Airport> airports = airportRepo.findAll();
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("flight", flight.get());
        model.addAttribute("airports", airports);
        return "/manage/editFlight";
    }

    @PostMapping("flight/edit")
    public String postEditFlight(@ModelAttribute Flight flight) {
        flightRepository.save(flight);
        return "redirect:/allFlights";
    }

    @DeleteMapping("/flight/delete/{id}")
    public String DeleteFlight(@PathVariable(name = "id") String id) {
        Optional<Flight> flight = flightRepository.findById(id);
        flight.ifPresent(value -> flightRepository.delete(value));
        return "redirect:/allFlights";


    }
}
