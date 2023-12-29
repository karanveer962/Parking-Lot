package org.example;
public class ParkingLot {
    private AirportSecurity securityStaff;
    private ParkingLotOwner parkingLotOwner;
    public int MAX_CAPACITY = 10;
    public int count=0;

    public ParkingLot(AirportSecurity staff,ParkingLotOwner owner){
        this.securityStaff=staff;
        this.parkingLotOwner=owner;
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
            this.notifyOwnerIfSpaceAvailable();
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
    private void notifyOwnerIfSpaceAvailable() {
        if (parkingLotOwner != null && !isLotFull()) {
            parkingLotOwner.notifySpaceAvailable();
        }
    }
}
