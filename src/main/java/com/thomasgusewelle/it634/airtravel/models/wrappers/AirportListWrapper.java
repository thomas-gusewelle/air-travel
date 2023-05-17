package com.thomasgusewelle.it634.airtravel.models.wrappers;

import com.thomasgusewelle.it634.airtravel.models.Airport;

import java.util.ArrayList;
import java.util.List;

public class AirportListWrapper {
    private List<Airport> airports;

    public AirportListWrapper(){
        setAirports(new ArrayList<Airport>());
    }


    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }
}
