package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class Timekeeper extends Thread {
    private final int timeLimit; // time limit in seconds
    private final AtomicBoolean isTimeUp;

    public Timekeeper(int timeLimit, AtomicBoolean isTimeUp) {
        this.timeLimit = timeLimit;
        this.isTimeUp = isTimeUp;
        setDaemon(true); // mark the thread as daemon
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        while (true) {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

            if (elapsedTime >= timeLimit) {
                System.out.println("Time limit reached. Stopping the exploration.");
                isTimeUp.set(true); // set the flag to stop the exploration
                break;
            }

            System.out.println("Exploration time: " + elapsedTime + " seconds");

            try {
                Thread.sleep(1000); // sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
