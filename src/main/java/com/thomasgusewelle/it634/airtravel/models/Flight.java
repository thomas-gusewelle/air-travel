package com.thomasgusewelle.it634.airtravel.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Flight {
    private String startingLocation;
    private String endingLocation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private Integer numOfPeopleTraveling;

//Getters and Setters
    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getEndingLocation() {
        return endingLocation;
    }

    public void setEndingLocation(String endingLocation) {
        this.endingLocation = endingLocation;
    }

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
}
