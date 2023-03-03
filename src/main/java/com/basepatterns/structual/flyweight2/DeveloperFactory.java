package com.basepatterns.structual.flyweight2;

import java.util.HashMap;
import java.util.Map;

public class DeveloperFactory {
    private static final Map<String, Developer> developers = new HashMap<>();

    public Developer getDeveloperBySpecialty(String specialty){
        Developer developer = developers.get(specialty);

        if (developer == null) {
            switch (specialty) {
                case "java" -> {
                    System.out.println("Hiring Java developer...");
                    developer = () -> System.out.println("Java developer writes Java code ...");
                }
                case "c++" -> {
                    System.out.println("Hiring C++ developer...");
                    developer = () -> System.out.println("C++ developer writes C++ code ...");
                }
            }
            developers.put(specialty, developer);
        }
        return developer;
    }
}
