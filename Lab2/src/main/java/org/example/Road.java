package org.example;

public abstract class Road {
    protected RoadType type;
    protected double length;
    protected double speedLimit;
    protected Location startLocation;
    protected Location endLocation;

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Road(RoadType type, double length, double speedLimit) {
        this.type = type;
        this.length = length;
        this.speedLimit = speedLimit;
    }

    public RoadType getType() {
        return type;
    }

    public double getLength() {
        return length;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Road road = (Road) o;
        if ((Double.compare(road.length, length) == 0) && (Double.compare(road.speedLimit, speedLimit) == 0)) return false;
        return type == road.type;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(speedLimit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return type + " (" + length + " km, " + speedLimit + " km/h)";
    }

    public Location getOtherLocation(Location location) {
        if (location.equals(startLocation)) {
            return endLocation;
        } else if (location.equals(endLocation)) {
            return startLocation;
        } else {
            // Location 404
            return null;
        }
    }
}

