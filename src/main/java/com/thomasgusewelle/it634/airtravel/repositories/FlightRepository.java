package com.thomasgusewelle.it634.airtravel.repositories;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import com.thomasgusewelle.it634.airtravel.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    // Custom function for finding flights matching all criteria
//    StartingLocation & endingLocation & departureDate & returnDate
    List<Flight> findFlightsByStartingLocationAndEndingLocationAndDepartureDateAndReturnDate(Airport startingLocation, Airport endingLocation, Date departureDate, Date returnDate);
}
