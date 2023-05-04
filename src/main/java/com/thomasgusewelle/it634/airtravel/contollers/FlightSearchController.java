package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.testData.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FlightSearchController {

    private final List<Flight> flights = new ArrayList<>();


    //  Points to the HTML file for flight search
    @GetMapping("/search")
    public String flightSearchPage() {
        return "/flightSearch/flightSearch";
    }

    //   Points to the HTML file for showing search results
    @GetMapping("/results")
    public String flightSearchResultsPage(@RequestParam String startingLocation, @RequestParam String endingLocation, @RequestParam String departureDate, @RequestParam String returnDate, @RequestParam String numOfPeopleTraveling, Model model) {
        List<Flight> filteredFlights = new ArrayList<>();
//       TODO: add in filtering for dates as well.
        filteredFlights = flights.stream().filter(flight -> (flight.getStartingLocation().equals(startingLocation) && flight.getEndingLocation().equals(endingLocation))).collect(Collectors.toList());
        model.addAttribute("filteredFlights", filteredFlights);
        model.addAttribute("numOfPeopleTraveling", numOfPeopleTraveling);
        return "/flightSearch/searchResults";
    }

    @GetMapping("/allFlights")
    public String allFlightsPage(Model model) {
        model.addAttribute("flights", this.flights);
        return "/manage/allFlights";
    }

    @GetMapping("/addFlight")
    public String addFlightPage(Model model) {
        model.addAttribute("newFlight", new Flight());
        return "/manage/addFlight";
    }

    @PostMapping("/addFlight")
    public String addFlight(Model model, @ModelAttribute("newFlight") Flight newFlight) {
//        Date departureDate = new Date(newFlight.getDepartureDate());
        flights.add(newFlight);
        model.addAttribute("newFlight", newFlight);
        return "redirect:/allFlights";
    }


}
