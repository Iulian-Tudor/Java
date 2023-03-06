package org.example;

public class Highway extends Road {
    public Highway(double length, double speedLimit, Location startLocation, Location endLocation) {
        super(RoadType.HIGHWAY, length, speedLimit);
        double distance = Math.sqrt(Math.pow(endLocation.getX() - startLocation.getX(), 2)
                + Math.pow(endLocation.getY() - startLocation.getY(), 2));
        if (length < distance) {
            this.length = distance;
        } else {
            this.length = length;
        }
        this.speedLimit = speedLimit;
        this.startLocation = startLocation;
        this.endLocation = endLocation;

    }
}
