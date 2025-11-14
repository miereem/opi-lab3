package task3;

import org.example.task3.Dolphin;
import org.example.task3.Human;
import org.example.task3.Thing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Nested
    class CognitiveAbilitiesTest {

        private Human human;
        private Dolphin dolphin;

        private Thing thing;

        CognitiveAbilitiesTest() {
        }

        @BeforeEach
        void init() {
            human = new Human("Shawn", 120, "We are the smartest");
            dolphin = new Dolphin("Ocean Dolphin", 130, "We are the smartest");
            thing = new Thing("Wheel");
        }

        @Test
        @DisplayName("Check negative cognitive abilities")
        void checkNegativeCognitiveAbilities() {
            Throwable exception = assertThrows(Exception.class, () -> dolphin.decreaseCognitiveAbilitiesBy(1310));
            assertEquals("Impossible to Degrade Cognitive Abilities Further.", exception.getMessage());
        }

        @Test
        @DisplayName("Check beyond reach cognitive abilities")
        void checkBeyondReachCognitiveAbilities() {
            Throwable exception = assertThrows(Exception.class, () -> human.increaseCognitiveAbilitiesBy(1810));
            assertEquals("Impossible to Develop Cognitive Abilities Further.", exception.getMessage());
        }
        @Test
        @DisplayName("Check increase cognitive abilities")
        void checkIncreaseCognitiveAbilities() {
            human.increaseCognitiveAbilitiesBy(5);
            assertEquals(125, human.getCognitiveAbilities());
        }
        @Test
        @DisplayName("Check decrease cognitive abilities")
        void checkDecreaseCognitiveAbilities() {
            dolphin.decreaseCognitiveAbilitiesBy(5);
            assertEquals(125, dolphin.getCognitiveAbilities());
        }

        @Test
        @DisplayName("Check thing's owner")
        void checkThingsOwner() {
            human.create(thing);
            assertEquals(human.getName(), thing.getOwner());
        }
    }
}
