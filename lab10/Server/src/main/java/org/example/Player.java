package org.example;

public class Player {
    private String name;
    private long timeControlMillis;
    private long lastMoveTimeMillis;

    public Player(String name, long timeControlMillis) {
        this.name = name;
        this.timeControlMillis = timeControlMillis;
    }

    public Player(String name) {
        this(name, 50);
    }

    public String getName() {
        return name;
    }

    public long getTimeControlMillis() {
        return timeControlMillis;
    }

    public long getLastMoveTimeMillis() {
        return lastMoveTimeMillis;
    }

    public void setLastMoveTimeMillis(long lastMoveTimeMillis) {
        this.lastMoveTimeMillis = lastMoveTimeMillis;
    }

    public boolean hasTimeLeft(long currentTimeMillis) {
        return currentTimeMillis - lastMoveTimeMillis < timeControlMillis;
    }
}
