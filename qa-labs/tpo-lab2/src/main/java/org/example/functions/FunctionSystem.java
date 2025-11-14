package org.example.functions;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.example.logarithmic.Ln;
import org.example.logarithmic.Log;
import org.example.trigonometric.Cot;
import org.example.trigonometric.Sec;
import org.example.trigonometric.Sin;
import org.example.trigonometric.Tan;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.*;

public class FunctionSystem implements Function{

    private final Sin sin;
    private final Tan tan;
    private final Sec sec;

    private final Cot cot;
    private final Ln ln;
    private final Log log2;

    private final Log log10;

    public FunctionSystem() {
        this.sin = new Sin();
        this.tan = new Tan();
        this.sec = new Sec();
        this.cot = new Cot();
        this.ln = new Ln();
        this.log2 = new Log(2);
        this.log10 = new Log(10);
    }

    public FunctionSystem(Sin sin, Tan tan, Sec sec, Cot cot, Ln ln, Log log2, Log log10) {
        this.sin = sin;
        this.tan = tan;
        this.sec = sec;
        this.cot = cot;
        this.ln = ln;
        this.log2 = log2;
        this.log10 = log10;
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
        final BigDecimal INTER_PRECISION = new BigDecimal("0.0000000000000001");
        final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
        final BigDecimal correctedX = x.remainder(BigDecimalMath.pi(mc).multiply(new BigDecimal(2)));
        if (x.compareTo(ZERO) <= 0) {
            if (tan.calculate(correctedX, INTER_PRECISION).compareTo(BigDecimal.ZERO) == 0) return null;
            if (sec.calculate(correctedX, INTER_PRECISION).equals(ZERO)) return null;

            return (((sec.calculate(correctedX, INTER_PRECISION))
                    .divide(tan.calculate(correctedX, INTER_PRECISION), HALF_DOWN)
                    .pow(2)
                    .subtract(tan.calculate(correctedX, INTER_PRECISION)))
                    .divide(cot.calculate(correctedX, INTER_PRECISION)
                            .divide(tan.calculate(correctedX,INTER_PRECISION)
                                    .add(sec.calculate(correctedX, INTER_PRECISION)), HALF_UP), HALF_UP)
                    .subtract(sin.calculate(correctedX, INTER_PRECISION)))
                    .setScale(precision.scale(), HALF_EVEN);
        } else {
            if (ln.calculate(correctedX, INTER_PRECISION).equals(ZERO)) return null;
            if (log2.calculate(correctedX, INTER_PRECISION).equals(ZERO)) return null;

            return (ln.calculate(correctedX, INTER_PRECISION)
                    .pow(2)
                    .subtract(ln.calculate(correctedX, INTER_PRECISION))
                    .subtract(log10.calculate(correctedX, INTER_PRECISION)
                    .pow(2))
                    .divide(ln.calculate(correctedX, INTER_PRECISION), HALF_UP)
                    .divide(log2.calculate(correctedX, INTER_PRECISION)
                    .pow(3), HALF_UP))
                    .setScale(precision.scale(), HALF_EVEN);
        }
    }

}
