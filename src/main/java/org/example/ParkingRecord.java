package org.example;

import java.util.Date;

public class ParkingRecord {
    private String carNumber;
    private String color;
    private Date timestamp;

    public ParkingRecord(String carNumber, String color, Date timestamp) {
        this.carNumber = carNumber;
        this.color = color;
        this.timestamp = timestamp;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getColor() {
        return color;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}