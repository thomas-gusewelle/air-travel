package com.thomasgusewelle.it634.airtravel.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightSearchController {
    @GetMapping("/search")
   public String flightSearchPage(){
       return "/flightSearch/flightSearch";
   }

   @GetMapping("/results")
    public String flightSearchResultsPage(){
        return "/flightSearch/searchResults";
   }
}
