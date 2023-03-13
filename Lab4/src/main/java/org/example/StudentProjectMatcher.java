package org.example;

import com.github.javafaker.Faker;


import java.util.*;
import java.util.stream.Collectors;

public class StudentProjectMatcher {
    private final List<Student> students;
    private final Set<Project> projects;

    public StudentProjectMatcher(int numStudents, int numProjects, int maxPreferences) {
        Faker faker = new Faker();
        Random random = new Random();

        // studenti cu nume si preferinte random
        students = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            int numPreferences = random.nextInt(maxPreferences) + 1;
            Set<Project> admissibleProjects = new HashSet<>();
            while (admissibleProjects.size() < numPreferences) {
                admissibleProjects.add(new Project(faker.color().name()));
            }
            students.add(new Student(faker.name().fullName(), admissibleProjects));
        }

        // proiecte random
        projects = new HashSet<>();
        for (int i = 0; i < numProjects; i++) {
            projects.add(new Project(faker.animal().name()));
        }
    }

    public List<Student> getStudentsWithLowerPreferences() {
        // calculeaza average-ul de preferinte
        double avgNumPreferences = students.stream()
                .mapToInt(s -> s.getAdmissibleProjects().size())
                .average()
                .orElse(0);

        // filtreaza studentii cu preferinte mai mici
        return students.stream()
                .filter(s -> s.getAdmissibleProjects().size() < avgNumPreferences)
                .sorted()
                .collect(Collectors.toList());
    }

    public Map<Student, Project> match() {
        // Map mentru marriage
        Map<Student, Project> matches = new HashMap<>();

        // copie de lista de set de proiecte
        Set<Project> availableProjects = new HashSet<>(projects);

        // sortarea studentilor dupa nr de preferinte
        List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparingInt(s -> s.getAdmissibleProjects().size()))
                .collect(Collectors.toList());

        // fiecare student e legat cu primul proiect valabil din lista de preferinte
        for (Student student : sortedStudents) {
            for (Project project : student.getAdmissibleProjects()) {
                if (availableProjects.contains(project)) {
                    matches.put(student, project);
                    availableProjects.remove(project);
                    break;
                }
            }
        }

        return matches;
    }
}
