 package task1;

import org.example.task1.Cos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CosTest {

    @ParameterizedTest(name = "cos({0})")
    @DisplayName("Chck PI dots")
    @ValueSource(doubles = {-2 * Math.PI, -Math.PI, -0.5 * Math.PI, 0, 0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 2 * Math.PI})
    void checkPiDots(double param){
        assertAll(
                () -> assertEquals(Math.cos(param), Cos.cos(param, 100), 0.0001)
        );
    }

    @ParameterizedTest(name = "cos({0}) = {1}")
    @DisplayName("Check between dots [-1; +1]")
    @CsvFileSource(resources = "/table_values.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsMinusPiAndPi(double x, double y) {
        assertAll(
                () -> assertEquals(y, Cos.cos(x, 100), 0.0001)
        );
    }

    @Test
    @DisplayName("Check NaN")
    void checkCosNaN() {
        assertTrue(Double.isNaN(Cos.cos(Double.NaN, 100)));
    }



}
