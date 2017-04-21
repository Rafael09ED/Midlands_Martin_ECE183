package days.feb9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * EGR 283 B01
 * days.feb9.mathTest.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 2/9/2017
 */
public class mathTest {
    public static final String ANSI_RESET = "\u001B[0m ";
    public static final String ANSI_GREEN = "\u001B[32m ";
    public static final String ANSI_RED = "\u001B[31m ";

    public static void main(String[] args) {
        findAndDo(100000000);
        /* List<Integer> list = generate(1000000);
        for (int i = 1; i < list.size(); i++) {
            Integer integer = list.get(i);
            System.out.print(String.format("%1$4s", integer) + " | ");
            int difference = (integer - list.get(i - 1));
            System.out.print(String.format("%1$4s", difference) + " | ");
            Iterator<Integer> iterator = list.iterator();
            for (Integer factor : generate(difference)){
                if (factor.equals(iterator.next()))
                    System.out.print(ANSI_GREEN + String.format("%1$2s", factor) + "" + ANSI_RESET);
                else {
                    System.out.print(ANSI_RED + String.format("%1$2s", factor) + "" + ANSI_RESET);
                    System.out.flush();
                    System.exit(1);
                }
            }
            System.out.println();
        }*/
    }


    private static List<Integer> generate(int size) {
        List<Integer> returnList = new ArrayList<>();
        for (int i = 1; i < size; i++)
            if (isPrime(i))
                returnList.add(i);
        return returnList;
    }

    private static void findAndDo(int size) {
        List<Integer> returnList = new ArrayList<>();
        returnList.add(1);
        for (int i = 2; i < size; i++)
            if (isPrime(i)) {
                returnList.add(i);
                doForEach(returnList);
            }
    }

    private static void doForEach(List<Integer> list) {
        Integer integer = list.get(list.size() - 1);
        System.out.print(String.format("%1$4s", integer) + " | ");
        int difference = (integer - list.get(list.size() - 2));
        System.out.print(String.format("%1$4s", difference) + " | ");
        Iterator<Integer> iterator = list.iterator();
        for (Integer factor : allFactors(difference)) {
            if (factor.equals(iterator.next()))
                System.out.print(ANSI_GREEN + String.format("%1$2s", factor) + " " + ANSI_RESET);
            else {
                System.out.print(ANSI_RED + String.format("%1$2s", factor) + " " + ANSI_RESET);
                System.out.flush();
              //  System.exit(1);
            }
        }
        System.out.println();
    }

    public static ArrayList<Integer> allFactors(int a) {
        int upperLimit = (int) (Math.sqrt(a));
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = 1; i <= upperLimit; i += 1) {
            if (a % i == 0) {
                factors.add(i);
                if (i != a / i) {
                    factors.add(a / i);
                }
            }
        }
        Collections.sort(factors);
        return factors;
    }

    private static boolean isPrime(int val) {
        for (int j = 2; j < val; j++) {
            if (val % j == 0)
                return false;
        }
        return true;
    }
}
