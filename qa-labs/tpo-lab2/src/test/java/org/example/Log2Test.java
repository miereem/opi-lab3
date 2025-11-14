package org.example;

import org.example.logarithmic.Ln;
import org.example.logarithmic.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_DOWN;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Log2Test {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

    @Mock
    private Ln mockLn;
    @Spy
    private Ln spyLn;

    @Test
    void callLn() {
        final Log log = new Log(spyLn, 2);
        log.calculate(new BigDecimal(6), new BigDecimal("0.001"));

        verify(spyLn, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/ln.csv")
    void calculateWithLnMock(BigDecimal arg, BigDecimal expected) {
        when(mockLn.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(expected);
        when(mockLn.calculate(eq(new BigDecimal("2")), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("0.69314718056"));

        final Log log = new Log(mockLn, 2);
        assertEquals(
                new BigDecimal(Math.log(arg.doubleValue())/Math.log(2)).setScale(4, HALF_DOWN),
                log.calculate(arg, new BigDecimal("0.0001")));
    }

    @Test
    void throwArithmeticExceptionForZero() {
        final Log log = new Log(2);
        assertThrows(ArithmeticException.class, () -> log.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void throwArithmeticExceptionForLn() {
        when(mockLn.calculate(eq(new BigDecimal(53)), any(BigDecimal.class)))
                .thenThrow(new ArithmeticException());

        final Log log = new Log(mockLn,2);
        assertThrows(ArithmeticException.class, () -> log.calculate(new BigDecimal(53), DEFAULT_PRECISION));

    }


    @Test
    void calculateForOne() {
        final Log log = new Log(2);
        assertEquals(
                new BigDecimal(Math.log(1)/Math.log(2)).setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
                log.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPositive() {
        final Log log = new Log(2);
        assertEquals(
                new BigDecimal(Math.log(53)/Math.log(2)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                log.calculate(new BigDecimal(53), DEFAULT_PRECISION));
    }
}
