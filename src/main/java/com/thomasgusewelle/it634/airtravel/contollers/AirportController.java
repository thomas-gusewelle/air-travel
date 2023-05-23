package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.models.wrappers.AirportListWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

// CController for handling thymeleaf templates
@Controller
public class AirportController {

// Get for all airports. Using the api/airport route to get all airports
    @GetMapping("/admin/allAirports")
    public String getAllAirports(Model model){
      RestTemplate rest = new RestTemplate();
      AirportListWrapper airports = rest.getForObject("http://localhost:8080/api/airport", AirportListWrapper.class);
        if (airports != null) {
            model.addAttribute("airports", airports.getAirports());
        }
        return "/manage/airports/allAirports";
    }


// GET for creating airport.
//    Passes empty airport object to the template
    @GetMapping("/admin/airports/airport/create")
    public String getCreateAirport(Model model){
       model.addAttribute("createAirport", new Airport());
       return "/manage/airports/createAirport";
    }

//    Post mapping for creating an airport.
//    Takes in airport and passes it to api/airport/create POST route
    @PostMapping("admin/airports/airport/create")
    public String postCreateAirport(@ModelAttribute("createAirport") Airport airport){
        RestTemplate rest = new RestTemplate();
        Airport _airport = rest.postForObject("http://localhost:8080/api/airport/create", airport, Airport.class);
       return "redirect:/admin/allAirports";

    }

//    GET mapping for editing an airport
//    Takes in the id on the path and passes it to api/airport/{id} which returns the airport from the DB
    @GetMapping("/admin/airports/airport/edit/{id}")
    public String getEditAirport(Model model, @PathVariable(value = "id") String id){
        RestTemplate rest = new RestTemplate();
        Airport airport = rest.getForObject("http://localhost:8080/api/airport/" + id, Airport.class);
        model.addAttribute("airport", airport);
      return "/manage/airports/editAirport";
    }
//    POST mapping for editing an airport
//    Takes in the model and id from path and passes it to api/airport/{id} POST route
    @PostMapping("/admin/airports/airport/edit/{id}")
    public String postEditAirport(@PathVariable(value = "id") String id, @ModelAttribute("editAirport") Airport editAirport){
        RestTemplate rest = new RestTemplate();
        Airport _editAirport = rest.postForObject("http://localhost:8080/api/airport/" + id, editAirport, Airport.class);
        return "redirect:/admin/allAirports";
    }

//    DELETE mapping for deleting airport
//    refreshes the page by calling its own template
    @DeleteMapping("/admin/airports/airport/delete/{id}")
    public String deleteAirport(@PathVariable(value = "id") String id){
        RestTemplate rest = new RestTemplate();
        rest.delete("http://localhost:8080/api/airport/" + id);
        return "/manage/airports/allAirports";
    }
}
