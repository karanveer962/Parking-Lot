package org.example;

import java.util.ArrayList;
import java.util.List;

public class PoliceDepartment {

 private List<ParkingLot> parkingLots;

       public PoliceDepartment(){
           this.parkingLots=new ArrayList<ParkingLot>();
       }

    public List<String> findLocationOfParkedWhiteCars() {
        List<String> locations = new ArrayList<>();
        for(ParkingLot it:parkingLots) {
            locations.addAll(it.findLocationOfParkedWhiteCars());
        }
        return locations;
    }
    public void setParkingLots(ParkingLot parkingLot){
           parkingLots.add(parkingLot);
    }

    public List<ParkingRecord> findBlueToyotaCarsInfo() {
        List<ParkingRecord> records=new ArrayList<>();
        for(ParkingLot it:parkingLots) {
            records.addAll(it.findBlueToyotaCarsInfo());
        }
        return records;
    }
    public List<String> findParkedBMWInfo(){
        List<String> cars = new ArrayList<>();
        for(ParkingLot it:parkingLots) {
            cars.addAll(it.findParkedBMWInfo());
        }
        return cars;
    }

    public List<String> findCarsParkedLast30Minutes(){
        List<String> cars = new ArrayList<>();
        for(ParkingLot it:parkingLots) {
            cars.addAll(it.findCarParkedLast30Minutes());
        }
        return cars;
    }
    public List<String> findSmallHandicapCarsOnRows(String row){
        List<String> cars = new ArrayList<>();
        for(ParkingLot it:parkingLots) {
            if(it.getName().equalsIgnoreCase(row))
                cars.addAll(it.findSmallHandicapCarsOnRows());
        }
        return cars;
    }

    public List<String> getAllParkedCarsInfo(){
        List<String> cars = new ArrayList<>();
        for(ParkingLot it:parkingLots) {
                cars.addAll(it.getAllParkedCarsInfo());
        }
        return cars;
    }
}

