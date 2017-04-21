package days.feb9;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * EGR 283 B01
 * days.feb9.test.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 2/9/2017
 */
public class test {
    public static void main(String[] args) {
        int times = 0;
        int n = new Scanner(System.in).nextInt();
        LocalTime start = LocalTime.now();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    times++;
                }
            }
        }
        System.out.println(times);
        System.out.println(start.until(LocalTime.now(), ChronoUnit.MILLIS));
    }
}
