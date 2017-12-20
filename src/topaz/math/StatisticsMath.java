package topaz.math;

import java.util.Arrays;

/*
TODO: Method for calculating mode
 */
public class StatisticsMath {

    public static double mean(double... values) {
        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }

    public static double median(double... values) {
        Arrays.sort(values);
        if (values.length % 2 == 1) {
            //Odd number of values
            return values[values.length / 2 + 1];
        } else {
            //Even number of values
            double num1 = values[values.length / 2];
            double num2 = values[values.length / 2 + 1];
            return mean(num1, num2);
        }
    }

    public static double range(double... values) {
        Arrays.sort(values);
        return values[values.length - 1] - values[0];
    }

    public static double variance(double... values) {
        double mean = mean(values);

        double[] squaredDifferences = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            squaredDifferences[i] = Math.pow(values[i] - mean, 2);
        }

        return mean(squaredDifferences);
    }

    public static double standardDeviation(double... values) {
        double variance = variance(values);
        return Math.sqrt(variance);
    }
}