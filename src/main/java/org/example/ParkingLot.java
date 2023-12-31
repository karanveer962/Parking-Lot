package org.example;

import java.util.*;

public class ParkingLot {
    private AirportSecurity securityStaff;
    private ParkingLotOwner parkingLotOwner;
    private ParkingAttendant parkingAttendant;
    public int MAX_CAPACITY = 10;
    public int count=0;
    private HashMap<String, ParkingRecord> parkedCars = new HashMap<>();

    public void setSecurityStaff(AirportSecurity staff){
        this.securityStaff=staff;
    }
    public void setParkingLotOwner(ParkingLotOwner parkingLotOwner) {
        this.parkingLotOwner = parkingLotOwner;
    }
    public void setParkingAttendant(ParkingAttendant parkingAttendant) {
        this.parkingAttendant = parkingAttendant;
    }
    public boolean parkCarForFlight(String carNo,String colour,String carMaker,Date timestamp,String attendant) {
        if(!isLotFull()) {
            parkedCars.put(carNo,new ParkingRecord(carNo,colour,carMaker,timestamp,attendant));
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
            System.out.println(carNo +" UnParked ✅");
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
        if(parkedCars.containsKey(carNo))
            return parkedCars.get(carNo).getTimestamp();
        else
        return null;
    }

    public int getNumberOfParkedCars() {
        return count;
    }

    public List<String> findLocationOfParkedWhiteCars(){
        List<String> cars=new ArrayList<>();
            for(String it:parkedCars.keySet()){
                if(parkedCars.get(it).getColor().equalsIgnoreCase("white"))
                    cars.add(it);
            }
            return cars;
    }

    public List<ParkingRecord> findBlueToyotaCarsInfo() {
        List<ParkingRecord> records=new ArrayList<>();
        for(String it:parkedCars.keySet()){
            if(parkedCars.get(it).getCarMaker().equalsIgnoreCase("toyota") && parkedCars.get(it).getColor().equalsIgnoreCase("blue"))
                records.add(parkedCars.get(it));
        }
        return records;
    }
}
