package org.example.task3;

import lombok.Data;
import lombok.SneakyThrows;

@Data
public abstract class Race {

    private int cognitiveAbilities;
    private String perception;

    public Race(int cognitiveAbilities, String perception) {
        this.cognitiveAbilities = cognitiveAbilities;
        this.perception = perception;
    }

    @SneakyThrows
    public void increaseCognitiveAbilitiesBy(int n) {
            if (this.cognitiveAbilities + n > 300) throw new Exception("Impossible to Develop Cognitive Abilities Further.");
            this.cognitiveAbilities += n;
    }

    @SneakyThrows
    public void decreaseCognitiveAbilitiesBy(int n) {
            if (this.cognitiveAbilities - n <= 0) throw new Exception("Impossible to Degrade Cognitive Abilities Further.");
            this.cognitiveAbilities -= n;
    }

    public void think() {
    }

    public void communicate() {
    }

    public void adaptEnvironment() {
    }

    public int getCognitiveAbilities() {
        return cognitiveAbilities;
    }

    public void setCognitiveAbilities(int cognitiveAbilities) {
        this.cognitiveAbilities = cognitiveAbilities;
    }

    public String getPerception() {
        return perception;
    }

    public void setPerception(String perception) {
        this.perception = perception;
    }
}