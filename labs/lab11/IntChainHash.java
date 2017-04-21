package labs.lab11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * EGR 283 B01
 * labs.lab11.IntChainHash.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/4/2017
 */
public class IntChainHash implements Hash<Integer> {
    private Chain[] values;
    private int count;
    private int filledIndices;

    public IntChainHash(int size) {
        values = new Chain[size];
    }

    @Override
    public void add(Integer val) {
        final int index = hashFunction(val);
        if (values[index] == null) {
            values[index] = new Chain();
            filledIndices++;
        }
        values[index].chain.add(val);
        count++;
    }

    @Override
    public double getFilledRatio() {
        return filledIndices / (values.length * 1d);
    }

    @Override
    public int getCountedCollisions() {
        return count-filledIndices;
    }

    @Override
    public boolean contains(Integer val) {
        Chain chain;
        return (chain = values[hashFunction(val)]) != null && chain.chain.contains(val);
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

    @Override
    public int hashFunction(Integer val) {
        return val % values.length;
    }

    private static class Chain {
        public List<Integer> chain = new LinkedList<>();

        @Override
        public String toString() {
            return chain.toString();
        }
    }
}
