package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.models.Flight;
import com.thomasgusewelle.it634.airtravel.repositories.AirportRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FlightSearchController {
private AirportRepository repo;
    private final List<Flight> flights = new ArrayList<>();


    //  Points to the HTML file for flight search
    @GetMapping("/search")
    public String flightSearchPage(Model model) {
       List<Airport> airports = repo.findAll() ;
       model.addAttribute("airports", airports);
        return "/flightSearch/flightSearch";
    }

    //   Points to the HTML file for showing search results
    @GetMapping("/results")
    public String flightSearchResultsPage(@RequestParam String startingLocation, @RequestParam String endingLocation, @RequestParam String departureDate, @RequestParam String returnDate, @RequestParam String numOfPeopleTraveling, Model model) {
        List<Flight> filteredFlights;
//        Create date objects for parsing params
        Date _departureDate;
        Date _returnDate;
//       Create formatter to turn string into date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        try Catch block for parsing dates from strings.
        try {
            _departureDate = formatter.parse(departureDate);
            _returnDate = formatter.parse(returnDate);
        } catch (ParseException e) {
//            TODO: implement  better error handling for if dates are unable to parse
            e.printStackTrace();
            return "redirect:/";
        }
//        Filters all flights based on starting and ending location and depature and return date
        filteredFlights = flights.stream().filter(flight -> (flight.getStartingLocation().equals(startingLocation) && flight.getEndingLocation().equals(endingLocation) && flight.getDepartureDate().equals(_departureDate) && flight.getReturnDate().equals(_returnDate))).collect(Collectors.toList());
//        Pass the models to the view
        model.addAttribute("filteredFlights", filteredFlights);
        model.addAttribute("numOfPeopleTraveling", numOfPeopleTraveling);
//      return view
        return "/flightSearch/searchResults";
    }




}
