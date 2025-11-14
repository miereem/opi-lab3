package org.example;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.example.logarithmic.Log;
import org.example.trigonometric.Cos;
import org.example.trigonometric.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_DOWN;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CosTest {

    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

    @Mock private Sin mockSin;
    @Spy private Sin spySin;

    @Test
    void callSin() {
        final Cos cos = new Cos(spySin);
        cos.calculate(new BigDecimal(6), new BigDecimal("0.001"));

        verify(spySin, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/csv/sin.csv")
    void calculateWithSinMock(BigDecimal arg, BigDecimal stub) {
        when(mockSin.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(stub);

        final Cos cos = new Cos(mockSin);
        assertEquals(
                new BigDecimal(Math.cos(arg.doubleValue())).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                cos.calculate(arg, DEFAULT_PRECISION));
    }



    @Test
    void calculateForZero() {
        final Cos cos = new Cos();
        assertEquals(
                new BigDecimal(Math.cos(0)),
                cos.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPiDividedByTwo() {
        final Cos cos = new Cos();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
        assertEquals(
                new BigDecimal(Math.cos(Math.PI/2)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                cos.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void calculateForOne() {
        final Cos cos = new Cos();
        assertEquals(
                new BigDecimal(Math.cos(1)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                cos.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPeriodic() {
        final Cos cos = new Cos();
        assertEquals(
                new BigDecimal(Math.cos(-543)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                cos.calculate(new BigDecimal(-543), DEFAULT_PRECISION));
    }
}
