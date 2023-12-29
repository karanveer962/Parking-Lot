package org.example;
public class ParkingLot {
    private AirportSecurity securityStaff;
    public int MAX_CAPACITY = 10;
    public int count=0;

    public ParkingLot(AirportSecurity staff){
        this.securityStaff=staff;
    }
    public boolean parkCarForFlight() {
        if(!isLotFull()) {
            System.out.println("Vehicle Parked ✅");
            count++;
            return true;
        }
        else{
            System.out.println("Parking lot is full. Redirecting security staff...");
            notifySecurity();
            return false;
        }
    }
    public boolean unParkCar() {
        if(count==0){
            System.out.println("Error!!! No car in the Lot");
            return false;
        }
        else {
            System.out.println("Vehicle UnParked ✅");
            count--;
            return true;
        }
    }
    public boolean isLotFull() {
        return count == MAX_CAPACITY;
    }

    private void notifySecurity() {
        if (securityStaff != null) {
            securityStaff.notifyLotFull();
        }
    }
}
