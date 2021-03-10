package PowerSeries;

import java.math.BigDecimal;

public interface ArccosCalculator {
    static BigDecimal arccos(Double x, Integer seriesLength){
        ArccosPowerSeries powerSeries = new ArccosPowerSeries(x, seriesLength);
        if(isProperX(x)){
            if(isProperSeriesLength(seriesLength)){
                powerSeries.compute();
            } else {
                throw new IllegalArgumentException("Improper argument");
            }
        } else {
            throw new IllegalArgumentException("Improper argument");
        }
        return powerSeries.getResult();
    }

    static BigDecimal arccos(Double x){
        ArccosPowerSeries powerSeries = new ArccosPowerSeries(x);
        if(isProperX(x)){
            powerSeries.compute();
        } else {
            throw new IllegalArgumentException("Improper argument");
        }
        return powerSeries.getResult();
    }

    static boolean isProperX(Double x) {
        System.out.println(x);
        System.out.println(Math.abs(x));
        return Math.abs(x) <= 1d;
    }

    static boolean isProperSeriesLength(Integer seriesLength){
        return seriesLength > 0;
    }
}
