package labs.lab11;

import java.util.Arrays;

/**
 * EGR 283 B01
 * labs.lab11.IntQuadHash.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/4/2017
 */
public class IntQuadHash implements Hash<Integer> {

    private Integer[] values;
    private int count;
    private int countedCollisions;

    public IntQuadHash(int size) {
        values = new Integer[size];
    }

    @Override
    public void add(Integer val) {
        final int initialIndex = hashFunction(val);
        for (int i = 0, index = initialIndex; i < values.length; index = (initialIndex + ++i ^ 2) % values.length) {
            Integer stored = values[index];
            if (stored != null) {
                countedCollisions++;
                continue;
            }
            values[index] = val;
            count++;
            return;
        }
        throw new RuntimeException("Could not add value to hash");

    }

    @Override
    public double getFilledRatio() {
        return count / (values.length * 1d);
    }

    @Override
    public int getCountedCollisions() {
        return countedCollisions;
    }

    @Override
    public boolean contains(Integer val) {
        final int initialIndex = hashFunction(val);
        for (int i = 0, index = initialIndex; i < values.length; index = (initialIndex + ++i ^ 2) % values.length) {
            if (values[index] == null) return false;
            if (values[index].equals(val)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

    @Override
    public int hashFunction(Integer val) {
        return val % values.length;
    }

}
