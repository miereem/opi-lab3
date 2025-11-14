package org.example;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.example.trigonometric.Sin;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");


    @Test
    void calculateForZero() {
        final Sin sin = new Sin();
        assertEquals(new BigDecimal(Math.sin(0)).setScale(4, RoundingMode.HALF_UP), sin.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPiDividedByTwo() {
        final Sin sin = new Sin();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
        assertEquals(
                new BigDecimal(Math.sin(Math.PI/2)).setScale(4, RoundingMode.HALF_UP),
                sin.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void calculateForOne() {
        final Sin sin = new Sin();
        assertEquals(
                new BigDecimal(Math.sin(1)).setScale(4, RoundingMode.HALF_UP),
                sin.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPeriodic() {
        final Sin sin = new Sin();
        assertEquals(
                new BigDecimal(Math.sin(-113)).setScale(4, RoundingMode.HALF_UP),
                sin.calculate(new BigDecimal(-113), DEFAULT_PRECISION));
    }
}
