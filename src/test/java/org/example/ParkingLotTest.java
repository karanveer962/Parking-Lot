package org.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static junit.framework.Assert.*;

public class ParkingLotTest {
    private ParkingLot parkingLot;
    @Before
    public void setUp() {
        parkingLot = new ParkingLot();
    }
    @Test
    public void parkCarForFlight_ShouldReturnFalse_WhenLotCapacityIsZero() {
        parkingLot.MAX_CAPACITY=0;
        boolean isParked = parkingLot.parkCarForFlight();
        assertFalse(isParked);
        assertEquals(0,parkingLot.count);
    }
    @Test
    public void parkCarForFlight_ShouldReturnTrue_WhenLotCapacityIsMoreThanCount() {
        boolean isParked = parkingLot.parkCarForFlight();
        assertTrue(isParked);
        assertEquals(1,parkingLot.count);
    }
    @Test
    public void unParkCar_ShouldReturnTrue_WhenLotHasSomeCars() {
        parkingLot.parkCarForFlight();
        parkingLot.parkCarForFlight();
        boolean unParked = parkingLot.unParkCar();
        assertTrue(unParked);
        assertEquals(1,parkingLot.count);
    }
    @Test
    public void unParkCar_ShouldReturnFalse_WhenNoCarIsPresent() {
        boolean unParked = parkingLot.unParkCar();
        assertFalse(unParked);
        assertEquals(0,parkingLot.count);
    }
}
