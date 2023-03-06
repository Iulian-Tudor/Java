package org.example;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        // Create the cities, airport and gas station
        City city1 = new City("Ungheni", 40.7128, -74.0060, 20_000);
        City city2 = new City("Chisinau", 100.0522, -118.2437, 1_000_000);
        Airport airport1 = new Airport("Chisinau Airport", 90.6442, -100.7822, 50);
        GasStation gasStation1 = new GasStation("Tirex Gas Station", 80.7111, -74.0059, 21.75);
        City city3 = new City ("Cahul", 89, -200, 10_000);


        // Create the roads
        Highway E1 = new Highway(0.0, 130.0, city1, city2);
        Expressway A25 = new Expressway(0.0, 110.0, city2, airport1);
        CountryRoad C4 = new CountryRoad(0.0, 90.0, city1, gasStation1);
        CountryRoad C3 = new CountryRoad(0.0, 90.0, city1, city3);
        Expressway A23 = new Expressway(0.0, 110.0, city3, airport1);

        System.out.println(C4);
        System.out.println(A23);
        System.out.println(E1);
        System.out.println(C3);
        System.out.println(A25);
        System.out.println();
        System.out.println(city1);
        System.out.println(city3);
        System.out.println(city2);
        System.out.println();

        // Add the roads to the Djikstra
        Djikstra roads = new Djikstra();
        roads.addRoad(E1);
        roads.addRoad(A25);
        roads.addRoad(C4);
        roads.addRoad(C3);
        roads.addRoad(A23);


        List<Location> shortestRoute = roads.findShortestRoute(city1, airport1);
        System.out.println("Shortest path: " + shortestRoute);

        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;

        if (elapsedTime < 1000000) {
            System.out.println("Elapsed time: " + elapsedTime + " ns");
        } else {
            System.out.println("Elapsed time: " + elapsedTime / 1000000 + " ms");
        }

    }
}