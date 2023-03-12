package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Network network = new Network();

        Person ion = new Person("ion", new Date());
        Person bob = new Person("Bob", new Date());
        Programmer iulian = new Programmer("iulian", new Date(), "Java");
        Designer vasile = new Designer("vasile", new Date(), "UI/UX");

        Company Companie1 = new Company("Companie1");
        Company Companie2 = new Company("Companie2 Inc.");

        network.addNode(ion);
        network.addNode(bob);
        network.addNode(iulian);
        network.addNode(vasile);
        network.addNode(Companie1);
        network.addNode(Companie2);

        ion.addRelationship(bob, "Friend");
        ion.addRelationship(iulian, "Co-worker");
        bob.addRelationship(iulian, "Co-worker");
        iulian.addRelationship(Companie1, "Employer");
        vasile.addRelationship(Companie2, "Employer");

        Companie1.addEmployee(iulian, "Programmer");
        Companie2.addEmployee(vasile, "Designer");

        network.printNetwork();


    }
}