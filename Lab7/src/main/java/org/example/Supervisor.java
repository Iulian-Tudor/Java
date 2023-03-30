package org.example;

import java.util.ArrayList;
import java.util.List;

public class Supervisor {
    private final List<Robot> robots;

    public Supervisor(int numRobots, Map map) {
        robots = new ArrayList<>();
        for (int i = 0; i < numRobots; i++) {
            robots.add(new Robot("Robot " + i, map));
        }
    }

    public void start() {
        for (Robot robot : robots) {
            robot.start();
        }
    }

    public void pause() {
        for (Robot robot : robots) {
            robot.interrupt();
        }
    }

    public List<Integer> getTokens() {
        List<Integer> allTokens = new ArrayList<>();
        for (Robot robot : robots) {
            allTokens.addAll(robot.getTokens());
        }
        return allTokens;
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public Robot getRobot(int index) {
        if (index < 0 || index >= robots.size()) {
            throw new IllegalArgumentException("Invalid robot index");
        }
        return robots.get(index);
    }

}
