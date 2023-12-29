package org.example;

import org.junit.Test;
import static junit.framework.Assert.assertTrue;

public class ParkingLotTest {
    @Test
    public void parkCarForFlight_ShouldReturnTrue_ForEveryDriverJourney() {
        ParkingLot parkingLot = new ParkingLot();
        boolean isParked = parkingLot.parkCarForFlight();
        assertTrue(isParked);
    }
}
