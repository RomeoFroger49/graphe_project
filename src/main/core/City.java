package main.core;

import java.util.Arrays;

public enum City {
    PARIS("Paris"),
    CAEN("Caen"),
    LILLE("Lille"),
    DIJON("Dijon"),
    NANCY("Nancy"),
    LYON("Lyon"),
    GRENOBLE("Grenoble"),
    RENNES("Rennes"),
    BORDEAUX("Bordeaux"),
    NANTES("Nantes");


    private final String label;

    City(String label) {
        this.label = label;
    }

    /** Libellé lisible (ex: "Paris"). */
    public String label() {
        return label;
    }

    public static String[] listLabels() {
        return Arrays.stream(values())
                .map(City::label)
                .toArray(String[]::new);
    }

}
