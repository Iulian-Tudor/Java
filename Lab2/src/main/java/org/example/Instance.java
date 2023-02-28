package org.example;

import java.util.ArrayList;
import java.util.List;

public class Instance {
    private List<City> cities;
    private List<Airport> airports;
    private List<GasStation> gasStations;
    private List<Highway> highways;
    private List<Expressway> expressways;
    private List<CountryRoad> countryRoads;

    public Instance() {
        cities = new ArrayList<>();
        airports = new ArrayList<>();
        gasStations = new ArrayList<>();
        highways = new ArrayList<>();
        expressways = new ArrayList<>();
        countryRoads = new ArrayList<>();
    }

    public void addCity(City city) {
        if (!cities.contains(city)) {
            cities.add(city);
        }
    }

    public void addAirport(Airport airport) {
        if (!airports.contains(airport)) {
            airports.add(airport);
        }
    }

    public void addGasStation(GasStation gasStation) {
        if (!gasStations.contains(gasStation)) {
            gasStations.add(gasStation);
        }
    }

    public void addHighway(Highway highway) {
        if (!highways.contains(highway)) {
            highways.add(highway);
        }
    }

    public void addExpressway(Expressway expressway) {
        if (!expressways.contains(expressway)) {
            expressways.add(expressway);
        }
    }

    public void addCountryRoad(CountryRoad countryRoad) {
        if (!countryRoads.contains(countryRoad)) {
            countryRoads.add(countryRoad);
        }
    }

    // getters and setters

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<GasStation> getGasStations() {
        return gasStations;
    }

    public void setGasStations(List<GasStation> gasStations) {
        this.gasStations = gasStations;
    }

    public List<Highway> getHighways() {
        return highways;
    }

    public void setHighways(List<Highway> highways) {
        this.highways = highways;
    }

    public List<Expressway> getExpressways() {
        return expressways;
    }

    public void setExpressways(List<Expressway> expressways) {
        this.expressways = expressways;
    }

    public List<CountryRoad> getCountryRoads() {
        return countryRoads;
    }

    public void setCountryRoads(List<CountryRoad> countryRoads) {
        this.countryRoads = countryRoads;
    }


}
