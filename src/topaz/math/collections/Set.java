package topaz.math.collections;

import java.util.ArrayList;
import java.util.Arrays;

/* TODO
getPowerSet() method
Support for infinite sets
N, Z, and R infinite sets
 */
public class Set<T> {

    public static Set EMPTY_SET = new Set();

    private ArrayList<T> elements;

    public Set(T... elementArray) {
        elements = new ArrayList<>();
        elements.addAll(Arrays.asList(elementArray));
    }

    public boolean add(T element) {
        if (contains(element)) {
            return false;
        }
        elements.add(element);
        return true;
    }

    public void remove(T element) {
        elements.remove(element);
    }

    public boolean contains(T element) {
        return elements.contains(element);
    }

    public boolean isSubsetOf(Set s) {
        for (int i = 0; i < getCardinality(); i++) {
            if (s.contains(elements.get(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean isSupersetOf(Set s) {
        return s.isSubsetOf(this);
    }

    public int getCardinality() {
        return elements.size();
    }

    public ArrayList<T> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Set == false) {
            return false;
        }
        if (getCardinality() != ((Set) object).getCardinality()) {
            return false;
        }
        for (int i = 0; i < getCardinality(); i++) {
            if (elements.get(i).equals(((Set) object).getElements().get(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
