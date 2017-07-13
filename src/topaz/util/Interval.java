package topaz.util;

public class Interval {

    private float min;
    private float max;

    public Interval(float min, float max) {
        this.min = min;
        this.max = max;
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