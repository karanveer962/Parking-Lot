package org.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

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
        parkingAttendant= new ParkingAttendant(parkingLot);
        parkingLot.setParkingAttendant(parkingAttendant);
        parkingLot.setSecurityStaff(securityStaff);
        parkingLot.setParkingLotOwner(parkingLotOwner);
    }
    @Test
    public void parkCarForFlight_ShouldReturnFalse_WhenLotCapacityIsZero() {
        parkingLot.MAX_CAPACITY=0;
        boolean isParked = parkingAttendant.parkCarForFlight();
        assertFalse(isParked);
        assertEquals(0,parkingLot.count);
    }
    @Test
    public void parkCarForFlight_ShouldReturnTrue_WhenLotCapacityIsMoreThanCount() {
        boolean isParked = parkingAttendant.parkCarForFlight();
        assertTrue(isParked);
        assertEquals(1,parkingLot.count);
    }
    @Test
    public void unParkCar_ShouldReturnTrue_WhenLotHasSomeCars() {
        parkingAttendant.parkCarForFlight();
        parkingAttendant.parkCarForFlight();
        boolean unParked = parkingAttendant.unParkCar();
        assertTrue(unParked);
        assertEquals(1,parkingLot.count);
    }
    @Test
    public void unParkCar_ShouldReturnFalse_WhenNoCarIsPresent() {
        boolean unParked = parkingAttendant.unParkCar();
        assertFalse(unParked);
        assertEquals(0,parkingLot.count);
    }

    //UC-4
    @Test
    public void testNotifySecurity_ShouldReturnTrue_WhenLotIsFull() {
        for (int i = 1; i <= parkingLot.MAX_CAPACITY; i++) {
            parkingAttendant.parkCarForFlight();
        }
        boolean isParked = parkingAttendant.parkCarForFlight();
        assertFalse(isParked);
        assertTrue(securityStaff.isNotified());  // Check if the security staff is notified
    }
    @Test
    public void testNotifySecurity_ShouldReturnFalse_WhenLotIsNotFull() {
        parkingAttendant.parkCarForFlight();
        assertFalse(securityStaff.isNotified());  // Check if the security staff is notified
    }

    //UC5
    @Test
    public void testNotifyOwner_ShouldReturnTrue_WhenSpaceIsAvailableAgain() {
        for (int i = 1; i <= parkingLot.MAX_CAPACITY; i++) {
            parkingAttendant.parkCarForFlight();
        }
        parkingAttendant.unParkCar();  // Unpark one car to create available space
        assertTrue(parkingLotOwner.isNotified());  // Check if the parking lot owner is notified
    }

}
