package org.example;

public class GasStation extends Location {
    private double gasPrice;

    public GasStation(String name, double x, double y, double gasPrice) {
        super(name, LocationType.GAS_STATION, x, y);
        this.gasPrice = gasPrice;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    @Override
    public String toString() {
        return super.toString() + " (Price: " + gasPrice + ")";
    }
}
