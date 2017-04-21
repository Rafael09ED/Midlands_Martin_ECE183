package labs.lab11;

import java.util.*;

/**
 * EGR 283 B01
 * labs.lab11.Driver.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/4/2017
 */
public class Driver {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        System.out.println("Enter size");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        sc.close();

        final IntChainHash chainHash = new IntChainHash(size);
        final IntQuadHash quadHash = new IntQuadHash(size);
        final List<Integer> valuesAdded = new LinkedList<>();

        Queue<Integer> percentFullQueue = new LinkedList<>(Arrays.asList(30,40,50,60,70,80,90, 100));
        for (int i = 0; i <= size*.9; i++) {
            int randomNumber = (int) (Math.random() * 500);
            chainHash.add(randomNumber);
            quadHash.add(randomNumber);
            valuesAdded.add(randomNumber);
            int percentFull = (int) ((i / (size * 1d)) * 100);
            if (percentFull >= percentFullQueue.peek() ) {
                System.out.println(percentFullQueue.remove() + "% full (" + i + "/" + size + " inserted)");
                System.out.println(" chain: " + chainHash.toString());
                System.out.println("  collisions: " + chainHash.getCountedCollisions());
                System.out.println("  % filled: " + (int) (chainHash.getFilledRatio() * 100) + "%");
                System.out.println(" quad: " + quadHash.toString());
                System.out.println("  collisions: " + quadHash.getCountedCollisions());
                System.out.println("  % filled: " + (int) (quadHash.getFilledRatio() * 100) + "%");
                System.out.println();
            }
        }
        Collections.shuffle(valuesAdded);

        System.out.println("Chain Check:");
        for (Integer integer : valuesAdded) {
            String color = chainHash.contains(integer) ? ANSI_GREEN : ANSI_RED;
            System.out.print(color + integer + ANSI_RESET + " ");
        }
        System.out.println();
        System.out.println("Quad Check:");
        for (Integer integer : valuesAdded) {
            String color = quadHash.contains(integer) ? ANSI_GREEN : ANSI_RED;
            System.out.print(color + integer + ANSI_RESET + " ");
        }
    }
}
