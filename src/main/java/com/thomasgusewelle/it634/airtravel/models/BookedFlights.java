package com.thomasgusewelle.it634.airtravel.models;

import jakarta.persistence.*;

//TODO: make relationships and add table. then setup repo and make page
@Entity
@Table(name = "booked_flights")
public class BookedFlights {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
