package labs.lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * EGR 283 B01
 * labs.lab3.Driver.java
 * Purpose: Runs a labs.lab3.Box labs.lab3.Blob Demo
 *
 * @author Rafael
 * @version 1.0 1/19/2017
 */
public class Driver {
    public static void main(String[] args) {
        new Driver();
    }

    /**
     * Creates 5 boxes of capacity 25, 30 blobs of a random weight between 1-4
     * Blobs are put into the boxes in order of creation
     */

    public Driver() {
        List<Box> boxes = new ArrayList<>();
        List<Blob> blobs = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            boxes.add(new Box(25.0));
        for (int i = 0; i < 30; i++)
            blobs.add(new Blob((Math.random() * 3 + 1)));

        for (int i = 0, j = 0; i < blobs.size(); i++) {
            while (!boxes.get(j).insert(blobs.get(i)))
                j++;
        }
        boxes.forEach(box -> System.out.println(box.toString()));
    }
}
