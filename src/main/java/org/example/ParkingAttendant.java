package org.example;

import java.util.Date;

public class ParkingAttendant {
    private ParkingLot parkingLot;
     public  ParkingAttendant(ParkingLot parkingLot){
          this.parkingLot=parkingLot;
      }
    public boolean parkCarForFlight(String carNo,Date timestamp) {
        return parkingLot.parkCarForFlight(carNo,timestamp);
    }
    public boolean unParkCar(String carNo){
         return parkingLot.unParkCar(carNo);
    }
    public Date getTimestampForParkedCar(String carNo){
         return parkingLot.getTimestampForParkedCar(carNo);
    }
}
