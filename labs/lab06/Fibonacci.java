package labs.lab6;

import java.util.Iterator;
import java.util.Scanner;

/**
 * EGR 283 B01
 * labs.lab6.Fibonacci.java
 *
 * @author Rafael
 * @version 1.0 2/9/2017
 */

public class Fibonacci implements Iterable<Long> {
    private final static int maxSize = 92;
    private final int size;

    public static void main(String[] args) {
        int times;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Type in the number of labs.lab6.Fibonacci numbers to calculate, -1 to quit");
            times = scanner.nextInt();
            if (times >= 0 && times <= maxSize)
                for (Long val : new Fibonacci(times))
                    System.out.print(val + " ");
            else if (times > 0)
                System.out.println("Not Valid Value");
            System.out.println();
        } while (times > 0);
    }

    /**
     * @param size max index of fibonacci numbers to generate.
     * @throws IndexOutOfBoundsException when size is less than 0 or greater than 92
     */
    public Fibonacci(int size) {
        if (size < 0 || size > maxSize)
            throw new IndexOutOfBoundsException("Size is not valid: " + size);
        this.size = size;
    }

    @Override
    public Iterator<Long> iterator() {
        return new FibonacciGenerator();
    }

    private class FibonacciGenerator implements Iterator<Long> {
        private int index = -1;
        private long val1 = 0, val2 = 1;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Long next() {
            if (++index == 0)
                return 0L;
            if (index == 1)
                return 1L;
            Long returnVal = val1 + val2;
            val1 = val2;
            return val2 = returnVal;
        }
    }
}
