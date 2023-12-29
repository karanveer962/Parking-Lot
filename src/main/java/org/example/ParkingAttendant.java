package org.example;

public class ParkingAttendant {
    private ParkingLot parkingLot;
     public  ParkingAttendant(ParkingLot parkingLot){
          this.parkingLot=parkingLot;
      }
    public boolean parkCarForFlight(String carNo) {
        return parkingLot.parkCarForFlight(carNo);
    }
    public boolean unParkCar(String carNo){
         return parkingLot.unParkCar(carNo);
    }
}
