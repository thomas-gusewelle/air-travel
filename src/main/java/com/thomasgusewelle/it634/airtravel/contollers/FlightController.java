package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.models.Flight;
import com.thomasgusewelle.it634.airtravel.repositories.AirportRepository;
import com.thomasgusewelle.it634.airtravel.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FlightController {
    @Autowired
    private FlightRepository repo;
    @Autowired
    private AirportRepository airportRepo;

    //    Gets all flights from the repo and adds them to model
    @GetMapping("/allFlights")
    public String allFlightsPage(Model model) {
        List<Flight> flights = repo.findAll();
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
        repo.save(newFlight);
        return "redirect:/allFlights";
    }
}
