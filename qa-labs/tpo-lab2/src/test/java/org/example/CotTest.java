package org.example;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.example.trigonometric.Cos;
import org.example.trigonometric.Cot;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CotTest {
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

        final Cot cot = new Cot(spySin, spyCos);
        cot.calculate(ONE, DEFAULT_PRECISION);

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

        final Cot cot = new Cot(mockSin, mockCos);
        assertEquals(
                new BigDecimal(Math.cos(5)/Math.sin(5)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                cot.calculate(arg, DEFAULT_PRECISION));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/csv/cos.csv")
    void calculateWithMockCos(BigDecimal arg, BigDecimal stub) {

        when(mockCos.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(stub);

        final Cot cot = new Cot(new Sin(), mockCos);

        assertEquals(
                new BigDecimal(Math.cos(arg.doubleValue())/Math.sin(arg.doubleValue())).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                cot.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void throwArithmeticExceptionForZero() {
        final Cot cot = new Cot();
        assertThrows(ArithmeticException.class, () -> cot.calculate(ZERO, DEFAULT_PRECISION));

    }

    @Test
    void calculateForPiDividedByTwo() {
        final Cot cot = new Cot();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
        assertEquals(
                new BigDecimal(Math.cos(Math.PI/2)/Math.sin(Math.PI/2)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN),
                cot.calculate(arg, DEFAULT_PRECISION));    }

    @Test
    void calculateForOne() {
        final Cot cot = new Cot();
        assertEquals(new BigDecimal(Math.cos(1)/Math.sin(1)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN), cot.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPeriodic() {
        final Cot cot = new Cot();
        assertEquals(new BigDecimal(Math.cos(134)/Math.sin(134)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN), cot.calculate(new BigDecimal(134), DEFAULT_PRECISION));
    }
}
