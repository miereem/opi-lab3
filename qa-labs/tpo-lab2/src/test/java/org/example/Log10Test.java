package org.example;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import org.example.logarithmic.Ln;
import org.example.logarithmic.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
public class Log10Test {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

    @Mock
    private Ln mockLn;
    @Spy
    private Ln spyLn;


    @Test
    void callLn() {
        final Log log = new Log(spyLn, 10);
        log.calculate(new BigDecimal(6), new BigDecimal("0.001"));

        verify(spyLn, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/ln.csv")
    void calculateWithLnMock(BigDecimal arg, BigDecimal expected) {
        when(mockLn.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(expected);
        when(mockLn.calculate(eq(new BigDecimal("10")), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("2.30258509299"));

        final Log log = new Log(mockLn, 10);
        assertEquals(
                new BigDecimal(Math.log10(arg.doubleValue())).setScale(5, HALF_DOWN),
                log.calculate(arg, new BigDecimal("0.00001")));
    }

    @Test
    void throwArithmeticExceptionForZero() {
        final Log log = new Log(10);
        assertThrows(ArithmeticException.class, () -> log.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void calculateForOne() {
        final Log log = new Log(10);
        assertEquals(
                new BigDecimal(Math.log10(1)).setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
                log.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPositive() {
        final Log log = new Log(10);
        assertEquals(
                new BigDecimal(Math.log10(53)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                log.calculate(new BigDecimal(53), DEFAULT_PRECISION));
    }

}
