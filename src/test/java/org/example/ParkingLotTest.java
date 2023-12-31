package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

public class ParkingLotTest {
    private ParkingLot parkingLot;
    private AirportSecurity securityStaff;
    private  ParkingLotOwner parkingLotOwner;
    private ParkingAttendant parkingAttendant ;
    @Before
    public void setUp() {
        parkingLot = new ParkingLot();
        securityStaff=new AirportSecurity();
        parkingLotOwner = new ParkingLotOwner();
        parkingAttendant= new ParkingAttendant("Raj");
        parkingAttendant.assignParkingLot(parkingLot);
        parkingLot.setParkingAttendant(parkingAttendant);
        parkingLot.setSecurityStaff(securityStaff);
        parkingLot.setParkingLotOwner(parkingLotOwner);
    }
    @Test
    public void parkCarForFlight_ShouldReturnFalse_WhenLotCapacityIsZero() {
        parkingLot.MAX_CAPACITY=0;
        boolean isParked = parkingAttendant.parkCarForFlight("car1","blue","Jaguar",new Date());
        assertFalse(isParked);
        assertEquals(0,parkingLot.count);
    }
    @Test
    public void parkCarForFlight_ShouldReturnTrue_WhenLotCapacityIsMoreThanCount() {
        boolean isParked = parkingAttendant.parkCarForFlight("car1","white","Ferrari",new Date());
        assertTrue(isParked);
        assertEquals(1,parkingLot.count);
    }
    @Test
    public void unParkCar_ShouldReturnTrue_WhenLotHasSomeCars() {
        parkingAttendant.parkCarForFlight("car1","red","Mercedes",new Date());
        parkingAttendant.parkCarForFlight("car2","green","Mercedes",new Date());
        boolean unParked = parkingAttendant.unParkCar("car1");
        assertTrue(unParked);
        assertEquals(1,parkingLot.count);
    }
    @Test
    public void unParkCar_ShouldReturnFalse_WhenNoCarIsPresent() {
        boolean unParked = parkingAttendant.unParkCar("car1");
        assertFalse(unParked);
        assertEquals(0,parkingLot.count);
    }

    //UC-4
    @Test
    public void testNotifySecurity_ShouldReturnTrue_WhenLotIsFull() {
        for (int i = 1; i <= parkingLot.MAX_CAPACITY; i++) {
            String str="Car"+i;
            parkingAttendant.parkCarForFlight(str,"white","Mahindra",new Date());
        }
        boolean isParked = parkingAttendant.parkCarForFlight("car11","yellow","Maruti",new Date());
        assertFalse(isParked);
        assertTrue(securityStaff.isNotified());  // Check if the security staff is notified
    }
    @Test
    public void testNotifySecurity_ShouldReturnFalse_WhenLotIsNotFull() {
        parkingAttendant.parkCarForFlight("car1","red","Hero",new Date());
        assertFalse(securityStaff.isNotified());  // Check if the security staff is notified
    }
    //UC5
    @Test
    public void testNotifyOwner_ShouldReturnTrue_WhenSpaceIsAvailableAgain() {
        for (int i = 1; i <= parkingLot.MAX_CAPACITY; i++) {
            String str="Car"+i;
            parkingAttendant.parkCarForFlight(str,"black","Toyota",new Date());
        }
        parkingAttendant.unParkCar("Car10");  // Unpark one car to create available space
        assertTrue(parkingLotOwner.isNotified());  // Check if the parking lot owner is notified
    }
    @Test
    public void testTrackTimestamp_WhenCarIsParked() {
        boolean isParked = parkingAttendant.parkCarForFlight("Car1","white","Honda",new Date());
        assertTrue(isParked);     // Check if the car is parked successfully
        Date timestamp = parkingAttendant.getTimestampForParkedCar("Car1");  // Get the timestamp when the car was parked
        assertNotNull(timestamp);    // Check if the timestamp is not null
    }
    @Test
    public void testEvenDistributionByParkingAttendant() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        // Set up the parking lots and parking attendant
        parkingAttendant.assignParkingLot(parkingLot1);
        parkingAttendant.assignParkingLot(parkingLot2);

        // Park multiple cars using the attendant
        parkingAttendant.parkCarForFlight("Driver1","white","Toyota",new Date());
        parkingAttendant.parkCarForFlight("Driver2", "green" ,"Hyundai",new Date());
        parkingAttendant.parkCarForFlight("Driver3", "white" ,"Mahindra",new Date());
        parkingAttendant.parkCarForFlight("Driver4","green","Toyota", new Date());

        assertEquals(2, parkingLot.count);
        assertEquals(1, parkingLot1.count);
        assertEquals(1, parkingLot2.count);
    }

    @Test
    public void testParkCarForHandicapDriver() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        parkingLot.MAX_CAPACITY=2;
        parkingLot1.MAX_CAPACITY=1;
        parkingLot2.MAX_CAPACITY=10;

        parkingAttendant.assignParkingLot(parkingLot1);
        parkingAttendant.assignParkingLot(parkingLot2);

        // Park multiple cars using the attendant
        parkingAttendant.parkCarForFlight("Driver1","white","Toyota",new Date());
        parkingAttendant.parkCarForFlight("Driver2", "green" ,"Hyundai",new Date());
        parkingAttendant.parkCarForFlight("Driver3", "white" ,"Mahindra",new Date());
        parkingAttendant.parkCarForFlight("Driver4","green","Toyota", new Date());

        // Park a car for a handicap driver
        boolean isParked = parkingAttendant.parkCarForHandicapDriver("Driver5","red" ,"Mahindra",new Date());

        // Check if the car is parked in the nearest lot
        assertTrue(isParked);
        assertTrue(parkingLot2.unParkCar("Driver5"));
    }

    @Test
    public void testFindLocationOfParkedWhiteCars_ShouldReturn2WhiteCarsLocation() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        policeDepartment.setParkingLots(parkingLot);
        policeDepartment.setParkingLots(parkingLot1);
        policeDepartment.setParkingLots(parkingLot2);

        // Park white cars in the lot
        parkingAttendant.parkCarForFlight("Driver1","white","Toyota",new Date());
        parkingAttendant.parkCarForFlight("Driver2", "green" ,"Hyundai",new Date());
        parkingAttendant.parkCarForFlight("Driver3", "white" ,"Mahindra",new Date());
        parkingAttendant.parkCarForFlight("Driver4","green","Toyota", new Date());

        // Find the location of parked white cars
        List<String> locations = policeDepartment.findLocationOfParkedWhiteCars();

        assertNotNull(locations);
        assertEquals(2, locations.size());
        assertTrue(locations.contains("Driver1"));
        assertTrue(locations.contains("Driver3"));
    }


    @Test
    public void testFindBlueToyotaCarsInfo_ShouldReturnOnly1CarInfo() {
        PoliceDepartment policeDepartment = new PoliceDepartment();

        ParkingAttendant parkingAttendant1=new ParkingAttendant("Arjun");
        parkingAttendant1.assignParkingLot(parkingLot);

        policeDepartment.setParkingLots(parkingLot);

        // Park white cars in the lot
        parkingAttendant.parkCarForFlight("Driver1","blue","Toyota",new Date());
        parkingAttendant.parkCarForFlight("Driver2", "green" ,"Hyundai",new Date());
        parkingAttendant1.parkCarForFlight("Driver3", "white" ,"Mahindra",new Date());
        parkingAttendant1.parkCarForFlight("Driver4","red","Toyota", new Date());

        // Find the location of parked white cars
        List<ParkingRecord> records = policeDepartment.findBlueToyotaCarsInfo();

        assertNotNull(records);
        assertEquals(1, records.size());
        assertTrue(records.get(0).getParkingAttendant().equalsIgnoreCase("raj"));
    }

    @Test
    public void testFindParkedBMWInfo() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        policeDepartment.setParkingLots(parkingLot);

        // Park white cars in the lot
        parkingAttendant.parkCarForFlight("Driver1","blue","BMW",new Date());
        parkingAttendant.parkCarForFlight("Driver2", "green" ,"Hyundai",new Date());
        parkingAttendant.parkCarForFlight("Driver3", "white" ,"BMW",new Date());
        parkingAttendant.parkCarForFlight("Driver4","red","BMW", new Date());

        // Find the location of parked white cars
        List<String> records = policeDepartment.findParkedBMWInfo();

        assertNotNull(records);
        assertEquals(3, records.size());
        assertTrue(records.contains("Driver1"));
        assertTrue(records.contains("Driver4"));

    }
}
