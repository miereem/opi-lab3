package org.example.trigonometric;

import org.example.functions.BaseFunction;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Sec extends BaseFunction {
    private final Cos cos;

    public Sec(final Cos cos) {
        super();
        this.cos = cos;
    }

    public Sec() {
        super();
        this.cos = new Cos();
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
            throws ArithmeticException {
        checkValidity(x, precision);
        final BigDecimal COS_PRECISION = new BigDecimal("0.00000001");

        final BigDecimal cosValue = cos.calculate(x, COS_PRECISION);

        if (cosValue.compareTo(ZERO) == 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        final BigDecimal result = BigDecimal.ONE.divide(cosValue, DECIMAL128.getPrecision(), HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
