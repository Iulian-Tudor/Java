package org.example;
public class Airport extends Location {
    private int numTerminals;

    public Airport(String name, double x, double y, int numTerminals) {
        super(name, LocationType.AIRPORT, x, y);
        this.numTerminals = numTerminals;
    }

    public int getNumTerminals() {
        return numTerminals;
    }

    @Override
    public String toString() {
        return super.toString() + " (Terminals: " + numTerminals + ")";
    }
}