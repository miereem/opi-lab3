package org.example;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.example.trigonometric.Cos;
import org.example.trigonometric.Sec;

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

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecTest {

    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

    @Mock
    private Cos mockCos;
    @Spy
    private Cos spyCos;

    @Test
    void callCos() {
        final Sec sec = new Sec(spyCos);
        sec.calculate(new BigDecimal(6), new BigDecimal("0.001"));

        verify(spyCos, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/csv/cos.csv")
    void calculateWithMockCos(BigDecimal arg, BigDecimal expected) {

        when(mockCos.calculate(eq(arg), any(BigDecimal.class)))
                .thenReturn(expected);
        final Sec sec = new Sec(mockCos);
        assertEquals(
                new BigDecimal(1/Math.cos(arg.doubleValue())).setScale(4, HALF_DOWN),
                sec.calculate(arg, new BigDecimal("0.0001")));
    }

    @Test
    void calculateForZero() {
        final Sec sec = new Sec();
        assertEquals(new BigDecimal(1/Math.cos(0)).setScale(DEFAULT_PRECISION.scale(), HALF_DOWN), sec.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void throwArithmeticExceptionForPiDividedByTwo() {
        final Sec sec = new Sec();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
        assertThrows(
                ArithmeticException.class, () -> sec.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void calculateForOne() {
        final Sec sec = new Sec();
        assertEquals(new BigDecimal(1/Math.cos(1)).setScale(DEFAULT_PRECISION.scale(), HALF_UP), sec.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void calculateForPeriodic() {
        final Sec sec = new Sec();
        assertEquals(new BigDecimal(1/Math.cos(-543)).setScale(DEFAULT_PRECISION.scale(), HALF_UP), sec.calculate(new BigDecimal(-543), DEFAULT_PRECISION));
    }
}
