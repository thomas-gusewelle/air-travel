package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.models.Flight;
import com.thomasgusewelle.it634.airtravel.repositories.AirportRepository;
import com.thomasgusewelle.it634.airtravel.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class FlightSearchController {
    @Autowired
    private AirportRepository repo;
    @Autowired
    private FlightRepository flightRepo;

    //  Points to the HTML file for flight search
    @GetMapping("/search")
    public String flightSearchPage(Model model) {
        List<Airport> airports = repo.findAll();
        model.addAttribute("airports", airports);
        return "/flightSearch/flightSearch";
    }

    //   Points to the HTML file for showing search results
    @GetMapping("/results")
    public String flightSearchResultsPage(@RequestParam String startingLocation, @RequestParam String endingLocation, @RequestParam String departureDate, @RequestParam String returnDate, @RequestParam String numOfPeopleTraveling, Model model) throws Exception {
//        Take ID string from url and find the airport object
        Optional<Airport> _startingLocation = repo.findById(startingLocation);
        Optional<Airport> _endingLocation = repo.findById(endingLocation);
        if (_endingLocation.isEmpty() || _startingLocation.isEmpty()) {
            throw new Exception("Error finding airports");
        }
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
        List<Flight> filteredFlights = flightRepo.findFlightsByStartingLocationAndEndingLocationAndDepartureDateAndReturnDate(_startingLocation.get(), _endingLocation.get(), _departureDate, _returnDate);
//        Filters all flights based on starting and ending location and departure and return date
//        Pass the models to the view
        model.addAttribute("filteredFlights", filteredFlights);
        model.addAttribute("numOfPeopleTraveling", numOfPeopleTraveling);
//      return view
        return "/flightSearch/searchResults";
    }


}
