package labs.lab3;

import java.text.DecimalFormat;

/**
 * EGR 283 B01
 * labs.lab3.Blob.java
 * Purpose: Represents a labs.lab3.Blob Entity
 *
 * @author Rafael
 * @version 1.0 1/19/2017
 */
public class Blob implements BlobInterface {
    private final double weight;

    /**
     * Creates a blob with a default weight of 2
     */
    public Blob() {
        weight = 2;
    }

    /**
     * creates a labs.lab3.Blob
     *
     * @param weight the weight of the new blob
     * @throws IllegalArgumentException when the weight is >4 or < 1
     */
    public Blob(double weight) {
        if (weight > 4 || weight < 1)
            throw new IllegalArgumentException("Invalid labs.lab3.Blob Weight: " + weight);
        this.weight = weight;
    }

    /**
     * @return the weight of the blob
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * @return details the weight of the blob
     */
    @Override
    public String toString() {
        return "labs.lab3.Blob w/ weight of " + new DecimalFormat("#.00").format(weight);
    }
}
