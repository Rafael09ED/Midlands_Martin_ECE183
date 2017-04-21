package labs.lab5;

import java.util.List;

/**
 * EGR 283 B01
 * CircularList.java
 * Purpose: Creates a list that loops at the end of the list
 *
 * @author Rafael
 * @version 1.0 1/31/2017
 */
public class CircularList implements CircularListI {
    /**
     * head: keeps track of the start of the circular list
     * tail: keeps track of the end of the circular list
     * count: keeps track of the size of the list
     */
    private Node<String> head = null, tail = null;
    private int count = 0;

    public CircularList() {
    }

    public CircularList(List<String> strings) {
        this();
        strings.forEach(this::add);
    }

    /**
     * @return the size of the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * @return true if the list is empty, false if there are elements in the listv
     */
    @Override
    public boolean isEmpty() {
        return count <= 0;
    }

    /**
     * @param item string to find in the list
     * @return true if the string is in the list, false if it is not
     */
    @Override
    public boolean contains(String item) {
        if (head == null)
            return false;
        Node<String> ptr = head.getNext();
        for (int index = 0; index < count; index++, ptr = ptr.getNext())
            if (ptr.getData().equals(item))
                return true;
        return false;
    }

    /**
     * @param item the string to add to the end of the list
     */
    @Override
    public void add(String item) {
        if (tail == null) {
            head = tail = new Node<>(item);
        } else {
            tail.setNext(new Node<>(item));
            tail = tail.getNext();
        }
        tail.setNext(head);
        count++;
    }

    /**
     * @param item the string to remove from the list
     * @return true if the string is removed, false if it was not removed, or was not in the list
     */
    @Override
    public boolean remove(String item) {
        int index = indexOf(item);
        if (index >= count || index < 0)
            return false;
        remove(index);
        return true;
    }

    /**
     * empties/clears the list
     */
    @Override
    public void clear() {
        head = tail = null;
        count = 0;
    }

    /**
     * @param index the index of the requested string
     * @return the string at the requested index
     * @throws IndexOutOfBoundsException when index is less than zero or the list is empty
     */
    @Override
    public String get(int index) {
        if (index < 0 || head == null)
            throw new IndexOutOfBoundsException("Invalid Index: " + index);

        Node<String> ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.getNext();
        }
        return ptr.getData();
    }

    /**
     * @param index   the index of the string to be replaced
     * @param element the string that will replace the original string
     * @throws IndexOutOfBoundsException when index is < 0 or the list is empty
     */
    @Override
    public void set(int index, String element) {
        if (index < 0 || head == null)
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        if (tail == head) {
            tail = head = new Node<>(element);
            tail.setNext(head);
            return;
        }
        Node<String> ptr = tail;
        for (int i = -1; i < index - 1; i++) {
            ptr = ptr.getNext();
        }
        Node<String> temp = ptr.getNext();
        ptr.setNext(new Node<>(element, ptr.getNext().getNext()));
        if (temp == tail)
            tail = ptr.getNext();
        else if (temp == head)
            head = ptr.getNext();
    }

    /**
     * @param index the index of where the string will be added. A zero reassigns the head
     * @param item  the string to insert
     */
    @Override
    public void add(int index, String item) {
        if (index < 0 || (head == null && index != 0))
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        if (index == 0) {
            if (head == null)
                tail = head = new Node<>(item);
            else
                head = new Node<>(item, head);
            tail.setNext(head);
        } else {
            Node<String> ptr = head;
            for (int i = 0; i < index - 1; i++) {
                ptr = ptr.getNext();
            }
            ptr.setNext(new Node<>(item, ptr.getNext()));
            if (ptr == tail)
                tail = ptr.getNext();
        }
        count++;
    }

    /**
     * @param index the index of the string to remove
     * @return the string that was removed
     */
    @Override
    public String remove(int index) {
        String returnString;
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        if (head == null)
            return null;
        if (tail == head) {
            returnString = head.getData();
            clear();
        } else {
            Node<String> ptr = tail;
            for (int i = -1; i < index - 1; i++)
                ptr = ptr.getNext();
            returnString = ptr.getNext().getData();
            if (ptr.getNext() == head) {
                head = head.getNext();
                tail.setNext(head);
            } else if (ptr.getNext() == tail) {
                ptr.setNext(head);
                tail = ptr;
            } else {
                ptr.setNext(ptr.getNext().getNext());
            }
            count--;
        }
        return returnString;
    }

    /**
     * @param item the string to find the index of
     * @return the index of the string
     */
    @Override
    public int indexOf(String item) {
        if (head == null) return -1;
        Node<String> ptr = head;
        for (int index = 0; index < count; index++, ptr = ptr.getNext())
            if (ptr.getData().equals(item))
                return index;
        return -1;
    }

    /**
     * offsets the start of the list forward one
     */
    @Override
    public void moveFirst() {
        moveFirst(1);
    }

    /**
     * @param N how far to offset the start of the list
     *          Accepts negative values
     */
    @Override
    public void moveFirst(int N) {
        if (head == null || head == tail) return;
        while (N < 0) N += count;
        for (int i = 0; i < N; i++) {
            tail = head;
            head = head.getNext();
        }
    }

    @Override
    public String toString() {
        if (head == null)
            return "null";
        StringBuilder builder = new StringBuilder("[" + head.getData() + ", ");
        for (Node<String> ptr = head.getNext(); ptr != head; ptr = ptr.getNext())
            builder.append(ptr.getData()).append(", ");
        builder.setLength(builder.length() - 2);
        builder.append("]. ");
        return builder.toString();
    }
}
