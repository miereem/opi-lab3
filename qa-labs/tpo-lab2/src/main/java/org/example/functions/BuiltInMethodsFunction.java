package org.example.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_DOWN;
import static java.math.RoundingMode.HALF_UP;

public class BuiltInMethodsFunction implements Function {

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {

        double doubleX = x.doubleValue();

        if (doubleX <= 0) {
            double sec = 1.0 / Math.cos(doubleX);
            double tan = Math.tan(doubleX);
            double cot = 1.0 / Math.tan(doubleX);
            double numerator = Math.pow(sec / tan, 2) - tan;
            double denominator = cot / (tan + sec);
            double result = (numerator / denominator) - Math.sin(doubleX);
            if (Double.isNaN(result)) {
                return null;
            }
            return new BigDecimal(result).setScale(precision.scale(), HALF_UP);
        } else {
            double ln = Math.log(doubleX);
            double log10 = Math.log10(doubleX);
            double log2 = Math.log(doubleX) / Math.log(2);
            double numerator = (Math.pow(ln, 2) - ln - Math.pow(log10, 2))/ln;
            double denominator = Math.pow(log2, 3);
            double result = numerator / denominator;
            if (Double.isNaN(result)) {
                return null;
            }
            return new BigDecimal(result).setScale(precision.scale(), HALF_DOWN);
        }
    }
}
