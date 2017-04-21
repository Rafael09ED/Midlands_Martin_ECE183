package labs.lab5;

public interface CircularListI extends ListI {
    /**
     * moveFirst moves the pointer to the first element of the list up one.
     * If myList contains [one,two,three,four], after myList.moveFirst(); it would then
     * contain [two,three,four,one]
     */
    public void moveFirst();

    /**
     * moveFirst(N) moves the pointer to the first element of the list up N.
     * If myList contains [one,two,three,four], after myList.moveFirst(3); it would then
     * contain [four,one,two,three]
     *
     * @param N the number of nodes to move forward
     */
    public void moveFirst(int N);
}