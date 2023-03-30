package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Robot extends Thread {
    private final String name;
    private final Map map;
    private int tokensPlaced;
    private final SharedMemory sharedMemory;
    private int x;
    private int y;
    private List<Integer> tokens;

    public Robot(String name, Map map) {
        this.name = name;
        this.map = map;
        this.sharedMemory = new SharedMemory(map.getSize());
        x = ThreadLocalRandom.current().nextInt(map.getSize());
        y = ThreadLocalRandom.current().nextInt(map.getSize());
    }

    public List<Integer> getTokens() {
        return tokens;
    }


    public void pause(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Invalid pause time");
        }
        if (millis == 0) {
            this.interrupt();
        } else {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                // Ignore interrupted exception
            }
        }
    }

    @Override
    public void run() {
        while (!map.isCompleted()) {
            // Check if the current cell has been visited
            if (!map.isVisited(x, y)) {
                // Extract tokens and store them in the cell
                List<Integer> tokenList = sharedMemory.extractToken();
                int[] tokens = tokenList.stream().mapToInt(Integer::intValue).toArray();
                map.setTokens(x, y, tokens);

                // Mark the cell as visited
                map.setVisited(x, y);

                // Print a message
                System.out.println(name + " visited cell (" + x + "," + y + ")");
            }

            // Move to a neighboring cell
            int dx = ThreadLocalRandom.current().nextInt(-1, 2);
            int dy = ThreadLocalRandom.current().nextInt(-1, 2);
            int nx = x + dx;
            int ny = y + dy;
            if (nx >= 0 && nx < map.getSize() && ny >= 0 && ny < map.getSize() && (nx != x || ny != y)) {
                x = nx;
                y = ny;
            }
        }
    }
}
