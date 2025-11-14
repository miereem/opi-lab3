package org.example;

import org.example.logarithmic.Ln;
import org.example.logarithmic.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

    @Mock
    private Ln mockLn;
    @Spy
    private Ln spyLn;

    @Test
    void callLn() {
        final Log log = new Log(spyLn, 5);
        log.calculate(new BigDecimal(6), new BigDecimal("0.001"));

        verify(spyLn, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @Test
    void calculateWithLnMock() {
        final BigDecimal arg = new BigDecimal(126);

        when(mockLn.calculate(eq(new BigDecimal(126)), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("4.8362819"));
        when(mockLn.calculate(eq(new BigDecimal(5)), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("1.6094379"));

        final Log log = new Log(mockLn, 5);
        assertEquals(
                new BigDecimal(Math.log(126)/Math.log(5)).setScale(6, RoundingMode.HALF_UP),
                log.calculate(arg, new BigDecimal("0.000001")));
    }

    @Test
    void throwArithmeticExceptionForZero() {
        final Log log = new Log(5);
        assertThrows(ArithmeticException.class, () -> log.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void calculateForOne() {
        final Log log = new Log(5);
        assertEquals(
                new BigDecimal(Math.log(1)/Math.log(5)).setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
                log.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPositive() {
        final Log log = new Log(5);
        assertEquals(
                new BigDecimal(Math.log(53)/Math.log(5)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                log.calculate(new BigDecimal(53), DEFAULT_PRECISION));
    }

}
