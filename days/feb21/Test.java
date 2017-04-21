package days.feb21;

import java.util.Scanner;

/**
 * EGR 283 B01
 * days.feb21.Test.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 2/21/2017
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(covert(scanner.nextInt(), scanner.nextInt()));
        for (String s : "".split(" ")) {

        }
    }

    public static String covert(int number, int base) {
        if (number < base)
            return rep(number, base) + "";
        else
            return covert(number / base, base) + rep(number % base, base);
    }

    public static char rep(int val, int base) {
        if (val < 10)
            return (val + "").charAt(0);
        else
            return (char) ('A' - 1 + val - 9);
    }
}
