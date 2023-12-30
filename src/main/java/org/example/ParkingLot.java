package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
public class ParkingLot {
    private AirportSecurity securityStaff;
    private ParkingLotOwner parkingLotOwner;
    private ParkingAttendant parkingAttendant;
    public int MAX_CAPACITY = 10;
    public int count=0;
    private HashMap<String, Date> parkedCars = new HashMap<>();

    public void setSecurityStaff(AirportSecurity staff){
        this.securityStaff=staff;
    }
    public void setParkingLotOwner(ParkingLotOwner parkingLotOwner) {
        this.parkingLotOwner = parkingLotOwner;
    }
    public void setParkingAttendant(ParkingAttendant parkingAttendant) {
        this.parkingAttendant = parkingAttendant;
    }
    public boolean parkCarForFlight(String carNo, Date timestamp) {
        if(!isLotFull()) {
            parkedCars.put(carNo,timestamp);
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
    public boolean unParkCar(String carNo) {
        if(count==0 || !parkedCars.containsKey(carNo)){
            System.out.println("Error!!! No car in the Lot");
            return false;
        }
        else {
            System.out.println(carNo +"UnParked ✅");
            parkedCars.remove(carNo);
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
    public Date getTimestampForParkedCar(String carNo){
        return parkedCars.getOrDefault(carNo, null);
    }

    public int getNumberOfParkedCars() {
        return count;
    }
}
