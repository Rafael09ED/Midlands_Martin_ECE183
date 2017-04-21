package labs.lab5;

/**
 * Node.java
 * Node Class for Set Interface
 *
 * @param <T>
 * @author Martin
 */
public class Node<T> {
    T data;
    Node<T> next;

    public Node() {
    }

    public Node(T d) {
        this.data = d;
    }

    public Node(T d, Node<T> n) {
        this.data = d;
        this.next = n;
    }

    public void setData(T d) {
        this.data = d;
    }

    public void setNext(Node<T> n) {
        this.next = n;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    @Override
    public String toString() {
        return " " + this.data;
    }
}
