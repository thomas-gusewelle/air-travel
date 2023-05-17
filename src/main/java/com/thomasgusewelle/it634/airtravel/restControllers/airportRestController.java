package com.thomasgusewelle.it634.airtravel.restControllers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.repositories.airportRepository;
import com.thomasgusewelle.it634.airtravel.models.wrappers.AirportListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/airport")
public class airportRestController {
    @Autowired
    private airportRepository repo;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportListWrapper> getAllAirports() {
        AirportListWrapper wrapper = new AirportListWrapper();
        List<Airport> airports = repo.findAll();
        wrapper.setAirports(airports);
        System.out.println("this is the airports " + airports);
        return ResponseEntity.ok().body(wrapper);
    }

    @GetMapping("/test2")
    public ResponseEntity<Airport> getAirport(){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
      List<Airport> airports = repo.findAll();
      Airport airport = airports.get(0);
      return new ResponseEntity<Airport>(airport, headers, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok().body("Hello World");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable(value = "id") Long id) {
        Optional<Airport> airport = repo.findById(id);
        return airport.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}