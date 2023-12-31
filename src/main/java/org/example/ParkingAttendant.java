package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingAttendant {
//    private ParkingLot parkingLot;

    private String name;
    public ParkingAttendant(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    private List<ParkingLot> assignedParkingLots = new ArrayList<>();
    public void assignParkingLot(ParkingLot parkingLot) {
        assignedParkingLots.add(parkingLot);
    }
    public boolean parkCarForFlight(String carNo,String colour,String carMaker,Date timestamp) {
        ParkingLot selectedParkingLot = findParkingLotWithMinimumCars();
        if (selectedParkingLot != null) {
            return selectedParkingLot.parkCarForFlight(carNo,colour,carMaker,timestamp,name);
        } else {
            System.out.println("No available parking lots.");
            return false;
        }
    }
    public boolean unParkCar(String carNo){
        for (ParkingLot parkingLot : assignedParkingLots) {
              if(parkingLot.unParkCar(carNo))
                  return true;
        }
        return false;
    }
    public Date getTimestampForParkedCar(String carNo){
        for (ParkingLot parkingLot : assignedParkingLots) {
            if(parkingLot.getTimestampForParkedCar(carNo)!=null)
                return parkingLot.getTimestampForParkedCar(carNo);
        }
         return null;
    }
    private ParkingLot findParkingLotWithMinimumCars() {
        if (assignedParkingLots.isEmpty()) {
            return null;
        }

        ParkingLot minCarsLot = assignedParkingLots.get(0);
        for (ParkingLot parkingLot : assignedParkingLots) {
            if (parkingLot.getNumberOfParkedCars() < minCarsLot.getNumberOfParkedCars()) {
                minCarsLot = parkingLot;
            }
        }
        return minCarsLot;
    }

    public boolean parkCarForHandicapDriver(String carNo,String colour,String carMaker,Date timestamp) {
        ParkingLot nearestLot = findNearestParkingLot();
        if (nearestLot != null) {
            return nearestLot.parkCarForFlight(carNo,colour,carMaker,timestamp,name);
        } else {
            System.out.println("No available parking lots.");
            return false;
        }
    }
    private ParkingLot findNearestParkingLot() {
        if (assignedParkingLots.isEmpty()) {
            return null;
        }

       for(ParkingLot parkingLot: assignedParkingLots){
           if(parkingLot.count < parkingLot.MAX_CAPACITY)
               return parkingLot;
       }
        return null;
    }
}
