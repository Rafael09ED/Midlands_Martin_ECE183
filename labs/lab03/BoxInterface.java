package labs.lab3;

public interface BoxInterface {
    /**
     * insert inserts a labs.lab3.Blob into the box
     *
     * @param b the labs.lab3.Blob to insert
     * @return true if the blob fits, or false if it doesn't
     */
    public boolean insert(BlobInterface b);

    /**
     * remainingCapacity accessor method
     *
     * @return the remaining capacity of the box (how many more pounds it can hold).
     */
    public double remainingCapacity();

    /**
     * toList
     *
     * @return a String listing all of the Blobs in the labs.lab3.Box, followed by the total weight
     */
    public String toString();
}