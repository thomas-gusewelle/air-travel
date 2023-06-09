package com.thomasgusewelle.it634.airtravel.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public String abbreviation;
    public String name;
    public String city;
    public String state;
    @OneToMany(mappedBy = "startingLocation", cascade = CascadeType.REMOVE)
    private List<Flight> startingFlights;

    @OneToMany(mappedBy = "endingLocation", cascade = CascadeType.REMOVE)
    private List<Flight> endingFlights;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String location) {
        this.city = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
