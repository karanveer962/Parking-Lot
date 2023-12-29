package org.example;

public class ParkingLotOwner {
    private boolean notified;

    public void notifySpaceAvailable() {
        notified = true;
        System.out.println("Parking lot owner has been notified: Space is available again.");
    }

    public boolean isNotified() {
        return notified;
    }
}
