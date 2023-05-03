package com.thomasgusewelle.it634.airtravel.testData;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//public record Flight(String startingLocation, String endingLocation, Date departureDate, Date returndate, Integer numOfPeopletraveling) {
//}

public class Flight {
    private String startingLocation;
    private String endingLocation;
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private Date returnDate;
    private Integer numOfPeopleTraveling;


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
