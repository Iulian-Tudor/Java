package org.example;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        int numRobots = 5;
        int timeLimit = 600; // time limit in seconds

        Map map = new Map(n);
        Supervisor supervisor = new Supervisor(numRobots, map);
        supervisor.start();

        AtomicBoolean isTimeUp = new AtomicBoolean(false);
        Timekeeper timekeeper = new Timekeeper(timeLimit, isTimeUp);
        timekeeper.setDaemon(true);
        timekeeper.start();

        Scanner scanner = new Scanner(System.in);
        while (!map.isCompleted() && !isTimeUp.get()) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            if (tokens[0].equals("start")) {
                if (tokens.length == 1) {
                    supervisor.start();
                } else if (tokens.length == 2) {
                    int robotIndex = Integer.parseInt(tokens[1]);
                    Robot robot = supervisor.getRobot(robotIndex);
                    if (robot != null) {
                        robot.start();
                    } else {
                        System.out.println("Robot " + robotIndex + " does not exist");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            } else if (tokens[0].equals("pause")) {
                if (tokens.length == 1) {
                    supervisor.pause();
                } else if (tokens.length == 3) {
                    int robotIndex = Integer.parseInt(tokens[1]);
                    Robot robot = supervisor.getRobot(robotIndex);
                    if (robot != null) {
                        if (tokens[2].equals("indefinitely")) {
                            robot.interrupt();
                        } else {
                            int pauseTime = Integer.parseInt(tokens[2]);
                            robot.pause(pauseTime);
                        }
                    } else {
                        System.out.println("Robot " + robotIndex + " does not exist");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            } else {
                System.out.println("Invalid command");
            }
        }

        supervisor.pause();
        System.out.println("Exploration completed");
    }
}
