package org.example;

public class AirportSecurity {
    private boolean notified;
    public void notifyLotFull() {
        notified = true;
        System.out.println("Airport security has been notified: Parking lot is full.");
    }
    public boolean isNotified() {
        return notified;
    }
}
