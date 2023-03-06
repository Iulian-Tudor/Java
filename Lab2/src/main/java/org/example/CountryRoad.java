package org.example;

public class CountryRoad extends Road {
    public CountryRoad(double length, double speedLimit, Location startLocation, Location endLocation) {

        // Distance este lungimea ecuclidiana calculata in functie de coordonate, care inlocuieste campul length daca acesta primeste o valoare prea mica.
        super(RoadType.COUNTRY_ROAD, length, speedLimit);
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



