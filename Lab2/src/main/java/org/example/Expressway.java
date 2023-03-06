package org.example;

public class Expressway extends Road {
    public Expressway(double length, double speedLimit, Location startLocation, Location endLocation) {

        super(RoadType.EXPRESSWAY, length, speedLimit);
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