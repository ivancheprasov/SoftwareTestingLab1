package powerSeries;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArccosPowerSeries {
    private BigDecimal x;
    private int seriesLength = 2500;
    private BigDecimal result = BigDecimal.valueOf(Math.PI/2);
    private static final BigDecimal SCALE_PRECISION = BigDecimal.valueOf(0.000001d);

    public ArccosPowerSeries(Double x, Integer seriesLength){
        this.x = BigDecimal.valueOf(x);
        this.seriesLength = seriesLength;
    }

    public ArccosPowerSeries(Double x){
        this.x = BigDecimal.valueOf(x);
    }

    public void compute() {
        BigDecimal result = getResult().subtract(getX());
        BigDecimal multiplier = BigDecimal.ONE;
        int power = 1;
        BigDecimal currentSubtrahend;
        int seriesLength = getSeriesLength();
        do {
            multiplier = multiplier.multiply(BigDecimal.valueOf(power)).divide(BigDecimal.valueOf(power+1));
            power+=2;
            currentSubtrahend = getX().pow(power).divide(BigDecimal.valueOf(power), SCALE_PRECISION.scale(), RoundingMode.CEILING).multiply(multiplier);
            result = result.subtract(currentSubtrahend);
            seriesLength -=1;
        } while(seriesLength > 0);
        this.result = result;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getResult() {
        return result;
    }

    public int getSeriesLength() {
        return seriesLength;
    }
}
