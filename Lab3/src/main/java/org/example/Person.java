package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class Person implements Comparable<Person>, Node {
    private String name;
    private Date birthDate;
    private Map<Node, String> relationships;

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        relationships = new HashMap<>();
    }

    //Adauga o relatie
    public void addRelationship(Node node, String description) {
        relationships.put(node, description);
    }

    //Returneaza tipul relatiei
    public String getRelationshipDescription(Node node) {
        return relationships.get(node);
    }

    public String getName() {
        return name;
    }

    //Compara numele pentru afisare dupa ordine alfabetica
    public int compareTo(Person other) {
        return name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return name + " (Importance: " + getNodeImportance() + ")\n" +
                "  Relationships:\n" +
                getRelationshipsString();
    }

    //Creaza string cu tipul relatiei pentru afisare.
    private String getRelationshipsString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : relationships.keySet()) {
            sb.append("  - ").append(node.getName());
            String description = relationships.get(node);
            if (description != null) {
                sb.append(" (").append(description).append(")");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getNodeImportance() {
        return relationships.size();
    }
}