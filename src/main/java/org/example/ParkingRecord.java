package org.example;

import java.util.Date;

public class ParkingRecord {
    private String carNumber;
    private String carMaker;
    private String color;
    private Date timestamp;
    private String parkingAttendant;
    private boolean handicapped;

    public ParkingRecord(String carNumber, String color,String carMaker, Date timestamp,String attendant) {
        this.carNumber = carNumber;
        this.color = color;
        this.timestamp = timestamp;
        this.carMaker=carMaker;
        parkingAttendant=attendant;
        this.handicapped=false;
    }
    public ParkingRecord(String carNumber, String color,String carMaker, Date timestamp,String attendant,boolean handicapped) {
        this.carNumber = carNumber;
        this.color = color;
        this.timestamp = timestamp;
        this.carMaker=carMaker;
        parkingAttendant=attendant;
        this.handicapped=handicapped;
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
    public String getCarMaker(){return carMaker;}
    public String getParkingAttendant(){
        return parkingAttendant;
    }

    public boolean isHandicapped() {
        return handicapped;
    }
}