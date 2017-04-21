package labs.lab4;

public interface SetInterface {
    /**
     * <code>add</code> will add the parameter to the set, unless
     * it is already there, in which case it returns false
     *
     * @param dbl is the Double to add
     * @return true if added, false if not
     */
    public boolean add(Double dbl);

    /**
     * <code>remove</code> will remove the parameter from the set,
     * unless it is not there, in which case it returns false
     *
     * @param dbl is the Double to remove
     * @return true if removed, false if not
     */
    public boolean remove(Double dbl);

    /**
     * <code>contains</code> will return true if the parameter is
     * in the set, else false
     *
     * @param dbl is the Double to Test
     * @return true if dbl is in the set, false if not
     */
    public boolean contains(Double dbl);

    /**
     * <code>empty</code> will return true if the set is empty, else false
     *
     * @return true if the set is empty, false if not
     */
    public boolean empty();

    /**
     * <code>size</code> will return the cardinality of the set
     *
     * @return the number of elements in the set
     */
    public int size();
}