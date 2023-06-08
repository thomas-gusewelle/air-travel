package com.thomasgusewelle.it634.airtravel.models;

import jakarta.persistence.*;

//TODO: make relationships and add table. then setup repo and make page
@Entity
@Table(name = "bookedFlights")
public class BookedFlights {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private CustomUser user;
    private Flight startingFlight;
    private Flight endingFlight;
    private Airport startingLocation;
    private Airport endingLocation;
}
