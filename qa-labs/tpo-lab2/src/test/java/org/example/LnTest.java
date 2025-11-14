package org.example;

import org.example.logarithmic.Ln;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LnTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.00001");

    @Test
    void throwArithmeticExceptionForZero() {
        final Ln ln = new Ln();
        assertThrows(
                ArithmeticException.class, () -> ln.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void calculateForOne() {
        final Ln ln = new Ln();
        assertEquals(
                new BigDecimal(Math.log(1)),
                ln.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPositive() {
        final Ln ln = new Ln();
        assertEquals(
                new BigDecimal(Math.log(4)).setScale(5, RoundingMode.HALF_UP),
                ln.calculate(new BigDecimal(4), DEFAULT_PRECISION));
    }

    @Test
    void throwArithmeticExceptionForNegative() {
        final Ln ln = new Ln();
        assertThrows(
                ArithmeticException.class,
                () -> ln.calculate(new BigDecimal(-4), DEFAULT_PRECISION));
    }
}
