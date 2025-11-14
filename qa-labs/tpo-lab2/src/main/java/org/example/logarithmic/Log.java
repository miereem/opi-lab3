package org.example.logarithmic;

import org.example.functions.BaseFunction;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

public class Log extends BaseFunction {
    private final Ln ln;
    private final int base;

    public Log(final int base) {
        super();
        this.ln = new Ln();
        this.base = base;
    }

    public Log(final Ln ln, final int base) {
        super();
        this.ln = ln;
        this.base = base;
    }


    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
            throws ArithmeticException {

        final BigDecimal LN_PRECISION = new BigDecimal("0.000000000000001");
        checkValidity(x, precision);

        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        final BigDecimal result =
                ln.calculate(x, LN_PRECISION)
                        .divide(
                                ln.calculate(new BigDecimal(base), LN_PRECISION),
                                DECIMAL128.getPrecision(),
                                HALF_UP);
        return result.setScale(precision.scale(), HALF_UP);
    }
}
