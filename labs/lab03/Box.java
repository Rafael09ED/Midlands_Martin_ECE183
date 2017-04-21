package labs.lab3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * EGR 283 B01
 * labs.lab3.Box.java
 * Purpose: Represents a labs.lab3.Box Entity
 *
 * @author Rafael
 * @version 1.0 1/19/2017
 */
public class Box implements BoxInterface {
    private final Double capacity; // lbs
    private final List<BlobInterface> storage = new ArrayList<>();

    /**
     * Creates box w/ default capacity of 25
     */
    public Box() {
        capacity = 25.0;
    }

    /**
     * Creates a box
     *
     * @param capacity capacity of the box.
     * @throws IllegalArgumentException when capacity is <= 0
     */
    public Box(Double capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Invalid labs.lab3.Box Capacity: " + capacity);
        this.capacity = capacity;
    }

    /**
     * Inserts blob into the box if it can
     *
     * @param b the labs.lab3.Blob to insert
     * @return if it was inserted
     */
    @Override
    public boolean insert(BlobInterface b) {
        if (b.getWeight() > remainingCapacity())
            return false;
        storage.add(b);
        return true;
    }

    /**
     * @return the free capacity remaining
     */
    @Override
    public double remainingCapacity() {
        return capacity - currentLoad();
    }

    /**
     * @return current capacity being used
     */
    private double currentLoad() {
        double totalWeight = 0;
        for (BlobInterface blobInterface : storage) {
            totalWeight += blobInterface.getWeight();
        }
        return totalWeight;
    }

    /**
     * @return String telling detailing the load of the box
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
                "labs.lab3.Box w/ load of " +
                        new DecimalFormat("#.00").format(currentLoad()) +
                        "/" +
                        capacity +
                        " lbs contains: ["
        );
        storage.forEach(blobInterface -> builder.append(blobInterface.toString()).append(", "));
        return builder.toString() + "]";
    }
}
