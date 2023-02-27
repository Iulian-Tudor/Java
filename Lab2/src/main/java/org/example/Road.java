package org.example;

class Road {
    private RoadType type;
    private double length;
    private double speedLimit;

    public Road(RoadType type, double length, double speedLimit) {
        this.type = type;
        this.length = length;
        this.speedLimit = speedLimit;
    }

    // Getters and setters
    public RoadType getType() {
        return type;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }


    @Override
    public String toString() {
        return "Road:" +
                "type=" + type +
                "| length=" + length +
                "| speedLimit=" + speedLimit;
    }
}

