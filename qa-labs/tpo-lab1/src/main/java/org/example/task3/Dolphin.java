package org.example.task3;

public class Dolphin extends Race {
    private String species;

    public Dolphin(String species, int cognitiveAbilities, String perception) {
        super(cognitiveAbilities, perception);
        this.species = species;
    }

}
