package com.thomasgusewelle.it634.airtravel.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "starting_airport_id")
    private Airport startingLocation;
    @ManyToOne
    @JoinColumn(name = "ending_airport_id")
    private Airport endingLocation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private Integer numOfPeopleTraveling;

    //Getters and Setters
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getNumOfPeopleTraveling() {
        return numOfPeopleTraveling;
    }

    public void setNumOfPeopleTraveling(Integer numOfPeopleTraveling) {
        this.numOfPeopleTraveling = numOfPeopleTraveling;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Airport getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(Airport startingLocation) {
        this.startingLocation = startingLocation;
    }

    public Airport getEndingLocation() {
        return endingLocation;
    }

    public void setEndingLocation(Airport endinglocation) {
        this.endingLocation = endinglocation;
    }
}
