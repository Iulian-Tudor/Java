package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Network {
    private List<Node> nodes;

    public Network() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void printNetwork() {
        // Masoara importanta nodurilor
        List<Node> sortedNodes = new ArrayList<>(nodes);
        Collections.sort(sortedNodes, new Comparator<Node>() {              //sorteaza dupa noduri
            @Override
            public int compare(Node n1, Node n2) {
                return n2.getNodeImportance() - n1.getNodeImportance();
            }
        });

        // Print
        for (Node node : sortedNodes) {
            System.out.println(node);
        }
    }
}
