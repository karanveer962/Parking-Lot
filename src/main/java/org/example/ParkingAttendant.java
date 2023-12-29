package org.example;

public class ParkingAttendant {
    private ParkingLot parkingLot;
     public  ParkingAttendant(ParkingLot parkingLot){
          this.parkingLot=parkingLot;
      }
    public boolean parkCarForFlight() {
        return parkingLot.parkCarForFlight();
    }
    public boolean unParkCar(){
         return parkingLot.unParkCar();
    }
}
