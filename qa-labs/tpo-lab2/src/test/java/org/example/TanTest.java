package org.example;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.example.logarithmic.Log;
import org.example.trigonometric.Cos;
import org.example.trigonometric.Sin;
import org.example.trigonometric.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_DOWN;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TanTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

    @Mock
    private Sin mockSin;
    @Mock private Cos mockCos;
    @Spy
    private Sin spySin;

    @Test
    void callSinAndCos() {
        final Cos cos = new Cos(spySin);
        final Cos spyCos = spy(cos);

        final Tan tan = new Tan(spySin, spyCos);
        tan.calculate(ZERO, DEFAULT_PRECISION);

        verify(spySin, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(spyCos, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @Test
    void calculateWithMockSinAndMockCos() {
        final BigDecimal arg = new BigDecimal(5);

        when(mockSin.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("-0.95892427"));
        when(mockCos.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("0.28366218"));

        final Tan tan = new Tan(mockSin, mockCos);
        assertEquals(
                new BigDecimal(Math.sin(5)/Math.cos(5)).setScale(4, RoundingMode.HALF_UP),
                tan.calculate(arg, DEFAULT_PRECISION));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/cos.csv")
    void calculateWithMockSinAndMockCos(BigDecimal arg, BigDecimal expected) {
        when(mockSin.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(new BigDecimal(Math.sin(arg.doubleValue())));
        when(mockCos.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(expected);
        final Tan tan = new Tan(mockSin, mockCos);
        assertEquals(
                new BigDecimal(Math.tan(arg.doubleValue())).setScale(4, HALF_DOWN),
                tan.calculate(arg, new BigDecimal("0.0001")));
    }

    @Test
    void calculateWithMockSin() {
        final BigDecimal arg = new BigDecimal(5);

        when(mockSin.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("-0.95892427"));

        final Tan tan = new Tan(mockSin, new Cos());
        assertEquals(
                new BigDecimal(Math.sin(5)/Math.cos(5)).setScale(4, RoundingMode.HALF_UP),
                tan.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void calculateWithMockCos() {
        final BigDecimal arg = new BigDecimal(5);

        when(mockCos.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("0.28366218"));

        final Tan tan = new Tan(new Sin(), mockCos);
        assertEquals(
                new BigDecimal(Math.sin(5)/Math.cos(5)).setScale(4, RoundingMode.HALF_UP),
                tan.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void calculateForZero() {
        final Tan tan = new Tan();
        assertEquals(
                new BigDecimal(Math.sin(0)/Math.cos(0)).setScale(4, RoundingMode.HALF_UP),
                tan.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void throwArithmeticExceptionForPiDividedByTwo() {
        final Tan tan = new Tan();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
        assertThrows(ArithmeticException.class, () -> tan.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void calculateForOne() {
        final Tan tan = new Tan();
        assertEquals(
                new BigDecimal(Math.sin(1)/Math.cos(1)).setScale(4, RoundingMode.HALF_UP),
                tan.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPeriodic() {
        final Tan tan = new Tan();
        assertEquals(
                new BigDecimal(Math.sin(134)/Math.cos(134)).setScale(4, RoundingMode.HALF_UP),
                tan.calculate(new BigDecimal(134), DEFAULT_PRECISION));
    }
}
