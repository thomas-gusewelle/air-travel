package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.repositories.airportRepository;
import com.thomasgusewelle.it634.airtravel.models.wrappers.AirportListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "test";
    }
    @GetMapping("admin/test")
    public String getTest(Model model){
        RestTemplate rest = new RestTemplate();

        Airport test = rest.getForObject("http://localhost:8080/api/airport/test2", Airport.class);
        model.addAttribute("test", test);
        return "test";
    }
}
