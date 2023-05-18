package com.thomasgusewelle.it634.airtravel.restControllers;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.repositories.airportRepository;
import com.thomasgusewelle.it634.airtravel.models.wrappers.AirportListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Denotes that the class is a REST controller and puts the base path at api/airport
@RestController
@RequestMapping("api/airport")
public class airportRestController {
//    Sets up the repo for the airport table
    @Autowired
    private airportRepository repo;

//    mapping for getting all of the airports
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportListWrapper> getAllAirports() {
        AirportListWrapper wrapper = new AirportListWrapper();
        List<Airport> airports = repo.findAll();
        wrapper.setAirports(airports);
        return ResponseEntity.ok().body(wrapper);
    }

//    Post for creating an airport
    @PostMapping("/create")
    public Airport createAirport(@RequestBody Airport airport){
       return repo.save(airport);
    }

//    Returns the airport based on an id if it exists
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable(value = "id") String id) {
        Optional<Airport> airport = repo.findById(id);
        return airport.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    Post for updating an airport
    @PostMapping("/{id}")
    public Airport updateAirportById(@PathVariable(value = "id") String id, @RequestBody Airport airport){
        return repo.save(airport);
    }
//    Delete for deleting an airport. First finds the airport and if it exists deletes it.
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable(value = "id") String id){
        Optional<Airport> airport = repo.findById(id);
        airport.ifPresent(value -> repo.delete(value));
    }
}