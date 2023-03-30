package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedMemory {
    private final List<Integer> tokens;
    private final int numTokens;

    public SharedMemory(int n) {
        numTokens = n * n * n;
        tokens = new ArrayList<>(numTokens);
        for (int i = 1; i <= numTokens; i++) {
            tokens.add(i);
        }
        Collections.shuffle(tokens);
    }

    public synchronized List<Integer> extractToken() {
        List<Integer> extractedTokens = new ArrayList<>();
        if (!tokens.isEmpty()) {
            for (int i = 0; i < numTokens; i++) {
                if (tokens.isEmpty()) {
                    break;
                }
                extractedTokens.add(tokens.remove(0));
            }
        }
        return extractedTokens;
    }
}