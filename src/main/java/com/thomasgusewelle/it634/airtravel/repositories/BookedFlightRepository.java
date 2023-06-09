package com.thomasgusewelle.it634.airtravel.repositories;

import com.thomasgusewelle.it634.airtravel.models.BookedFlights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedFlightRepository extends JpaRepository<BookedFlights, String> {
    List<BookedFlights> findBookedFlightsByUserId(String userId);


}
