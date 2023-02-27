package org.example;

public class Main {
    public static void main(String[] args) {

        Location city1 = new Location("Ungheni", LocationType.CITY, 40.7128, -74.0060);
        Location city2 = new Location("Chisinau", LocationType.CITY, 34.0522, -118.2437);
        Location airport1 = new Location("Chisinau Airport", LocationType.AIRPORT, 40.6442, -73.7822);
        Location gasStation1 = new Location("Tirex Gas Station", LocationType.GAS_STATION, 40.7111, -74.0059);


        System.out.println(city1);
        System.out.println(city2);
        System.out.println(airport1);
        System.out.println(gasStation1);


        Road highway1 = new Road(RoadType.HIGHWAY, 100.0, 65.0);
        Road expressway1 = new Road(RoadType.EXPRESSWAY, 50.0, 55.0);
        Road countryRoad1 = new Road(RoadType.COUNTRY_ROAD, 20.0, 45.0);

        System.out.println(highway1);
        System.out.println(expressway1);
        System.out.println(countryRoad1);
    }
}