package labs.lab11;

/**
 * EGR 283 B01
 * labs.lab11.Hash.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/4/2017
 */
public interface Hash<T> {
    /**
     * @param val value to add to the Map
     */
    void add(T val);

    /**
     * @param val value to get hash for
     * @return base index
     */
    int hashFunction(T val);

    /**
     * @return ratio of occupied array positions to array size
     */
    double getFilledRatio();

    /**
     * @return total number of collisions from attempting adds
     */
    int getCountedCollisions();

    /**
     * @param val value to look for
     * @return true if value is found, false if not
     */
    boolean contains(T val);
    //void setSize(int size) throws IllegalArgumentException;
}
