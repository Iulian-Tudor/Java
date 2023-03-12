package org.example;

import java.time.LocalDate;
import java.util.Date;

class Designer extends Person {
    private String skill;

    public Designer(String name, Date birthDate, String skill) {
        super(name, birthDate);
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    @Override
    public String toString() {
        return super.toString() + "  Designer skill: " + skill + "\n";
    }
}
