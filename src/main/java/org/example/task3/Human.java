package org.example.task3;

public class Human extends Race {

    private String name;

    public Human(String name, int cognitiveAbilities, String perception) {
        super(cognitiveAbilities, perception);
        this.name = name;
    }

    public void create(Thing thing) {
        thing.setOwner(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
