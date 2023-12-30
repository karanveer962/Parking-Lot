package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

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
        parkingAttendant= new ParkingAttendant();
        parkingAttendant.assignParkingLot(parkingLot);
        parkingLot.setParkingAttendant(parkingAttendant);
        parkingLot.setSecurityStaff(securityStaff);
        parkingLot.setParkingLotOwner(parkingLotOwner);
    }
    @Test
    public void parkCarForFlight_ShouldReturnFalse_WhenLotCapacityIsZero() {
        parkingLot.MAX_CAPACITY=0;
        boolean isParked = parkingAttendant.parkCarForFlight("car1",new Date());
        assertFalse(isParked);
        assertEquals(0,parkingLot.count);
    }
    @Test
    public void parkCarForFlight_ShouldReturnTrue_WhenLotCapacityIsMoreThanCount() {
        boolean isParked = parkingAttendant.parkCarForFlight("car1",new Date());
        assertTrue(isParked);
        assertEquals(1,parkingLot.count);
    }
    @Test
    public void unParkCar_ShouldReturnTrue_WhenLotHasSomeCars() {
        parkingAttendant.parkCarForFlight("car1",new Date());
        parkingAttendant.parkCarForFlight("car2",new Date());
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
            parkingAttendant.parkCarForFlight(str,new Date());
        }
        boolean isParked = parkingAttendant.parkCarForFlight("car11",new Date());
        assertFalse(isParked);
        assertTrue(securityStaff.isNotified());  // Check if the security staff is notified
    }
    @Test
    public void testNotifySecurity_ShouldReturnFalse_WhenLotIsNotFull() {
        parkingAttendant.parkCarForFlight("car1",new Date());
        assertFalse(securityStaff.isNotified());  // Check if the security staff is notified
    }
    //UC5
    @Test
    public void testNotifyOwner_ShouldReturnTrue_WhenSpaceIsAvailableAgain() {
        for (int i = 1; i <= parkingLot.MAX_CAPACITY; i++) {
            String str="Car"+i;
            parkingAttendant.parkCarForFlight(str,new Date());
        }
        parkingAttendant.unParkCar("Car10");  // Unpark one car to create available space
        assertTrue(parkingLotOwner.isNotified());  // Check if the parking lot owner is notified
    }
    @Test
    public void testTrackTimestamp_WhenCarIsParked() {
        boolean isParked = parkingAttendant.parkCarForFlight("Car1", new Date());
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
        parkingAttendant.parkCarForFlight("Driver1",  new Date());
        parkingAttendant.parkCarForFlight("Driver2",  new Date());
        parkingAttendant.parkCarForFlight("Driver3",  new Date());
        parkingAttendant.parkCarForFlight("Driver4",  new Date());

        assertEquals(2, parkingLot.count);
        assertEquals(1, parkingLot1.count);
        assertEquals(1, parkingLot2.count);
    }

}
