package org.example;

public class Map {
    private final int n;
    private final boolean[][] visited;
    private final int[][][] tokens;

    private final SharedMemory sharedMemory;


    public Map(int n) {
        this.n = n;
        sharedMemory = new SharedMemory(n);
        visited = new boolean[n][n];
        tokens = new int[n][n][n];
    }

    public int getSize() {
        return n;
    }
    public synchronized boolean isVisited(int x, int y) {
        return visited[x][y];
    }

    public synchronized void setVisited(int x, int y) {
        visited[x][y] = true;
    }

    public synchronized int[] getTokens(int x, int y) {
        return tokens[x][y];
    }

    public synchronized void setTokens(int x, int y, int[] tokens) {
        this.tokens[x][y] = tokens;
    }

    public synchronized boolean isCompleted() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
