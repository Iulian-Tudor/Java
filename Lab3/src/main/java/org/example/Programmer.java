package org.example;

import java.time.LocalDate;
import java.util.Date;

class Programmer extends Person {
    private String language;

    public Programmer(String name, Date birthDate, String language) {
        super(name, birthDate);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return super.toString() + "  Programming language: " + language + "\n";
    }
}