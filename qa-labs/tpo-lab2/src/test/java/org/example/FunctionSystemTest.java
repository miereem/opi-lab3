package org.example;

import org.example.functions.BuiltInMethodsFunction;
import org.example.functions.FunctionSystem;
import org.example.logarithmic.Ln;
import org.example.logarithmic.Log;
import org.example.trigonometric.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FunctionSystemTest {

    @Mock
    private Sin mockSin;
    @Mock
    private Cos mockCos;

    @Mock
    private Tan mockTan;
    @Mock
    private Sec mockSec;

    @Mock
    private Cot mockCot;
    @Mock
    private Ln mockLn;
    @Mock
    private Log mockLog2;

    @Mock
    private Log mockLog10;
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.00001");

    @Test
    void throwNullPointerExceptionForNullArgument() {
        final FunctionSystem system = new FunctionSystem();
        assertThrows(NullPointerException.class, () -> system.calculate(null, DEFAULT_PRECISION));
    }

    @Test
    void throwNullPointerExceptionForNullPrecision() {
        final FunctionSystem system = new FunctionSystem();
        assertThrows(NullPointerException.class, () -> system.calculate(new BigDecimal(5), null));
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/csv/func.csv")
    void calculateWithMocks(BigDecimal arg) {
        final FunctionSystem system;
        if(arg.compareTo(ZERO) > 0) {
            when(mockLn.calculate(eq(arg), any(BigDecimal.class)))
                    .thenReturn(new BigDecimal(Math.log(arg.doubleValue())));
            when(mockLog10.calculate(eq(arg), any(BigDecimal.class)))
                    .thenReturn(new BigDecimal(Math.log(arg.doubleValue()) / Math.log(10)));
            when(mockLog2.calculate(eq(arg), any(BigDecimal.class)))
                    .thenReturn(new BigDecimal(Math.log(arg.doubleValue()) / Math.log(2)));
            system = new FunctionSystem(mockSin, mockTan, mockSec, mockCot, mockLn, mockLog2, mockLog10);

        } else {
            system = new FunctionSystem();
        }



        final BuiltInMethodsFunction builtIn = new BuiltInMethodsFunction();

        assertEquals(
                builtIn.calculate(arg, DEFAULT_PRECISION),
                system.calculate(arg, DEFAULT_PRECISION));
    }


    @Test
    void undefinedForArgumentZero() {
        final FunctionSystem system = new FunctionSystem();
        assertNull((system.calculate(ZERO, DEFAULT_PRECISION)));
    }

    @Test
    void undefinedForArgumentOne() {
        final FunctionSystem system = new FunctionSystem();
        assertNull((system.calculate(ONE, DEFAULT_PRECISION)));
    }

    @Test
    void calculateForPositiveValue() {
        final FunctionSystem system = new FunctionSystem();
        final BuiltInMethodsFunction builtIn = new BuiltInMethodsFunction();
        assertEquals(builtIn.calculate(new BigDecimal(5), DEFAULT_PRECISION), system.calculate(new BigDecimal(5), DEFAULT_PRECISION));
    }

    @Test
    void calculateForNegativeValue() {
        final FunctionSystem system = new FunctionSystem();
        final BuiltInMethodsFunction builtIn = new BuiltInMethodsFunction();
        assertEquals(
                builtIn.calculate(new BigDecimal(-5.349), DEFAULT_PRECISION),
                system.calculate(new BigDecimal(-5.349), DEFAULT_PRECISION));
    }

}
