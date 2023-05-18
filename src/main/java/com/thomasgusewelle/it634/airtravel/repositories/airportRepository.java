package com.thomasgusewelle.it634.airtravel.repositories;

import com.thomasgusewelle.it634.airtravel.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface airportRepository extends JpaRepository<Airport, String> {
}
