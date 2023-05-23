package com.thomasgusewelle.it634.airtravel.repositories;

import com.thomasgusewelle.it634.airtravel.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {
}
