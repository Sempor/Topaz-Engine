package topaz.util;

public class Interval {

    private float min;
    private float max;

    public Interval(float num1, float num2) {
        if (num1 > num2) {
            max = num1;
            min = num2;
        } else {
            max = num2;
            min = num1;
        }
    }

    public Interval(Interval interval) {
        this.min = interval.min;
        this.max = interval.max;
    }

    public boolean overlaps(Interval interval) {
        if (min > interval.min && min < interval.max) {
            return true;
        } else if (max > interval.min && max < interval.max) {
            return true;
        } else if (interval.min > min && interval.min < max) {
            return true;
        } else if (interval.max > min && interval.max < max) {
            return true;
        }
        return false;
    }
}
