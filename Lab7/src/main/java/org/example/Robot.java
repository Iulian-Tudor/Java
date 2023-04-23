package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Robot extends Thread {
    private final String name;
    private final Map map;
    private final SharedMemory sharedMemory;
    private final boolean[][] visited;
    private final int[][] tokens;
    private int x;
    private int y;

    public Robot(String name, Map map) {
        this.name = name;
        this.map = map;
        this.sharedMemory = new SharedMemory(map.getSize());
        this.visited = new boolean[map.getSize()][map.getSize()];
        this.tokens = new int[map.getSize()][map.getSize()];
        x = ThreadLocalRandom.current().nextInt(map.getSize());
        y = ThreadLocalRandom.current().nextInt(map.getSize());
    }

    @Override
    public void run() {
        dfs(x, y);
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

    public List<Integer> getTokens() {
        List<Integer> allTokens = new ArrayList<>();
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                for (int k = 0; k < tokens[i][j]; k++) {
                    allTokens.add(map.getTokens(i, j)[k]);
                }
            }
        }
        return allTokens;
    }

    public void dfs(int x, int y) {
        // Mark the current cell as visited
        map.setVisited(x, y);

        // Extract tokens and store them in the cell
        List<Integer> tokenList = sharedMemory.extractToken();
        int[] tokens = tokenList.stream().mapToInt(Integer::intValue).toArray();
        map.setTokens(x, y, tokens);

        // Print a message
        System.out.println(name + " visited cell (" + x + "," + y + ") and placed tokens: " + tokenList);

        // Explore neighboring cells recursively
        int[][] neighbors = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up, right, down, left
        for (int[] neighbor : neighbors) {
            int nx = x + neighbor[0];
            int ny = y + neighbor[1];
            if (nx >= 0 && nx < map.getSize() && ny >= 0 && ny < map.getSize() && !map.isVisited(nx, ny)) {
                dfs(nx, ny);
            }
        }
    }
}
