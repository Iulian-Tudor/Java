package org.example;

import java.util.*;

public class Djikstra {
    protected Map<Location, List<Road>> graph;

    public Djikstra() {
        this.graph = new HashMap<>();
    }


    // Adauga drumul la lista de drumuri existente pentru clasa Djikstra
    public void addRoad(Road road) {
        Location start = road.getStartLocation();
        Location end = road.getEndLocation();
        graph.putIfAbsent(start, new ArrayList<>());
        graph.putIfAbsent(end, new ArrayList<>());
        graph.get(start).add(road);
        graph.get(end).add(road);
    }

    // Cauta si returneaza shortest path conform lungimile euclidiene pe care le calculeaza conditia de validare din cu extindere pe road
    public List<Location> findShortestRoute(Location start, Location end) {
        Queue<Location> queue = new LinkedList<>();
        Map<Location, Integer> distances = new HashMap<>();
        Map<Location, Location> previous = new HashMap<>();

        queue.offer(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            for (Road road : graph.get(current)) {
                Location neighbor = road.getOtherLocation(current);

                double distanceToNeighbor = distances.get(current) + road.getLength();
                if (!distances.containsKey(neighbor) || distanceToNeighbor < distances.get(neighbor)) {
                    distances.put(neighbor, (int) distanceToNeighbor);
                    previous.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        if (!previous.containsKey(end)) {
            return null; // path 404
        }

        List<Location> path = new ArrayList<>();
        Location current = end;
        while (current != null) {
            path.add(0, current);
            current = previous.get(current);
        }
        return path;
    }
}
