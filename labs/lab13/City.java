package labs.lab13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * EGR 283 B01
 * City.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/20/2017
 */
public class City {

    public final int id;

    public City(int id) {
        this.id = id;
    }

    public int getDistanceTo(City city) {
        return getDistance(id, city.id);
    }

    @Override
    public String toString() {
        return "City " + id;
    }

    private final static int[][] distanceMap;

    static {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\Rafael\\IdeaProjects\\EGR283\\src\\intercityDistances.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        distanceMap = new int[41][41];
        for (int i = 0; i < distanceMap.length; i++) {
            for (int j = 0; j < distanceMap.length; j++) {
                distanceMap[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }
        sc.close();
    }

    public static int getDistance(int city1, int city2) {
        return distanceMap[city1][city2];
    }
}
