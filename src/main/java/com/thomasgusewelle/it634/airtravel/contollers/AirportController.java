package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.repositories.airportRepository;
import com.thomasgusewelle.it634.airtravel.models.wrappers.AirportListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AirportController {
@Autowired
private airportRepository repo;

    @GetMapping("/admin/allAirports")
    public String getAllAirports(Model model){
      RestTemplate rest = new RestTemplate();
      AirportListWrapper airports = rest.getForObject("http://localhost:8080/api/airport", AirportListWrapper.class);
        if (airports != null) {
            model.addAttribute("airports", airports.getAirports());
        }

        return "/manage/airports/allAirports";
    }


    @GetMapping("/admin/airports/airport/edit/{id}")
    public String getEditAirport(Model model, @PathVariable(value = "id") String id){

        RestTemplate rest = new RestTemplate();
        Airport airport = rest.getForObject("http://localhost:8080/api/airport/" + id, Airport.class);
        model.addAttribute("airport", airport);
      return "/manage/airports/editAirport";
    }
    @PostMapping("/admin/airports/airport/edit/{id}")
    public String postEditAirport(@PathVariable(value = "id") String id, @ModelAttribute("editAirport") Airport editAirport){
        RestTemplate rest = new RestTemplate();
        Airport _editAirport = rest.postForObject("http://localhost:8080/api/airport/" + id, editAirport, Airport.class);
        return "redirect:/admin/allAirports";
    }
}
