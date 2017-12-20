package topaz.math;

import topaz.math.collections.Set;
import topaz.math.collections.OrderedPair;

public class SetMath {

    public static Set union(Set a, Set b) {
        Set s = new Set();
        for (int i = 0; i < a.getCardinality(); i++) {
            s.add(a.getElements().get(i));
        }
        for (int i = 0; i < b.getCardinality(); i++) {
            s.add(b.getElements().get(i));
        }
        return s;
    }

    public static Set intersection(Set a, Set b) {
        Set s = new Set();
        if (a.getCardinality() <= b.getCardinality()) {
            for (int i = 0; i < a.getCardinality(); i++) {
                if (b.contains(a.getElements().get(i))) {
                    s.add(a.getElements().get(i));
                }
            }
        } else {
            for (int i = 0; i < b.getCardinality(); i++) {
                if (a.contains(b.getElements().get(i))) {
                    s.add(b.getElements().get(i));
                }
            }
        }
        return s;
    }

    public static Set difference(Set a, Set b) {
        Set s = new Set();
        for (int i = 0; i < a.getCardinality(); i++) {
            if (b.contains(a.getElements().get(i))) {
                continue;
            }
            s.add(a.getElements().get(i));
        }
        return s;
    }

    public static Set symmetricDifference(Set a, Set b) {
        Set s = new Set();
        for (int i = 0; i < a.getCardinality(); i++) {
            if (b.contains(a.getElements().get(i))) {
                continue;
            }
            s.add(a.getElements().get(i));
        }
        for (int i = 0; i < b.getCardinality(); i++) {
            if (a.contains(b.getElements().get(i))) {
                continue;
            }
            s.add(b.getElements().get(i));
        }
        return s;
    }

    public static Set cartesianProduct(Set a, Set b) {
        Set s = new Set();
        for (int i = 0; i < a.getCardinality(); i++) {
            for (int j = 0; j < b.getCardinality(); j++) {
                s.add(new OrderedPair(a.getElements().get(i), b.getElements().get(j)));
            }
        }
        return s;
    }
}
