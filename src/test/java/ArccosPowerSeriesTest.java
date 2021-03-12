import PowerSeries.ArccosCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ArccosPowerSeriesTest {
    private static final Double TEST_VALUE_PRECISION = 0.015;
    private static final Double TEST_SERIES_LENGTH_PRECISION = 1d;

    @ParameterizedTest
    @CsvFileSource(resources = "/testValue.csv")
    public void testValue(Double value, Double expected) {
        double result = ArccosCalculator.arccos(value).doubleValue();
        Assertions.assertEquals(expected, result, TEST_VALUE_PRECISION);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testWrongValue.csv")
    public void testWrongValue(Double value, Double wrongResult) {
        double result = ArccosCalculator.arccos(value).doubleValue();
        Assertions.assertNotEquals(wrongResult, result, TEST_VALUE_PRECISION);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testSeriesLength.csv")
    public void testPrecision(Double value, Double expected, Integer seriesLength) {
        double result = ArccosCalculator.arccos(value, seriesLength).doubleValue();
        Assertions.assertEquals(expected, result, TEST_SERIES_LENGTH_PRECISION);
    }

    @Test
    public void testSeriesLengthExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(0d, -1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(0d, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(0d, Integer.MIN_VALUE));
    }

    @Test
    public void testValueExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(1.0001d));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(-1.0001d));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(Double.POSITIVE_INFINITY));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(Double.NEGATIVE_INFINITY));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(1.0001d, 100));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(-1.0001d, 100));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(Double.POSITIVE_INFINITY, 100));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArccosCalculator.arccos(Double.NEGATIVE_INFINITY, 100));
    }
}
