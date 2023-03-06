package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        Person ion = new Person("Ion");
        Person vasile = new Person("vasile");
        Person tudor = new Person("Tudor");
        Company google = new Company("Google");
        Company microsoft = new Company("Microsoft");

        ion.addFriend(vasile);
        vasile.addFriend(tudor);
        tudor.addFriend(ion);

        google.addEmployee(ion);
        google.addEmployee(vasile);
        microsoft.addEmployee(tudor);

        nodes.add(ion);
        nodes.add(vasile);
        nodes.add(tudor);
        nodes.add(google);
        nodes.add(microsoft);

        Collections.sort(nodes, Comparator.comparing(Node::getName));

        for (Node node : nodes) {
            System.out.println(node.getName());
        }
    }
}
