package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Flight;
import com.thomasgusewelle.it634.airtravel.repositories.FlightRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FlightController {
    private FlightRepository repo;

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
        model.addAttribute("newFlight", new Flight());
        return "/manage/addFlight";
    }

//    Post mapping for adding a flight
    @PostMapping("/addFlight")
    public String addFlight(Model model, @ModelAttribute("newFlight") Flight newFlight) {
       repo.save(newFlight);
        model.addAttribute("newFlight", newFlight);
        return "redirect:/allFlights";
    }
}
