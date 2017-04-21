package labs.lab9.old;

import java.util.Scanner;

/**
 * EGR 283 B01
 * labs.lab9.old.labs.lab9.TernarySearchTree.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 3/16/2017
 */
public class TernarySearchTree<T extends Comparable<T>> {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter # of values to insert");
        TernarySearchTree<Double> tree = new TernarySearchTree<>();
        int size = sc.nextInt();
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            Double val = (Math.random() * 1000);
            val = Math.floor(val * 100) / 100;
            System.out.print(val + ", ");
            tree.insert(val);
        }
        System.out.println("]");
        System.out.println("Tree Output: ");
        System.out.println(tree.toString());
        System.out.println("Enter values to remove. Quit with values < 0");
        double value;
        while ((value = sc.nextDouble()) > 0) {
            System.out.println(tree.remove(value));
            System.out.println("In    Order: " + tree.toString(0));
            System.out.println("Pre   Order: " + tree.toString(1));
            System.out.println("Post  Order: " + tree.toString(2));
            System.out.println("Level Order: " + tree.toString(3));
            System.out.println();
        }
        System.out.println("Exited");
    }

    private String toString(int i) {
        return root.toString(i);
    }

    private T remove(T value) {
        return root.delete(value);
    }

    private TernaryTreeNode<T> root;

    public boolean insert(T valueToInsert) {
        if (root == null)
            root = new TernaryTreeNode<>(valueToInsert);
        else return root.insert(valueToInsert);
        return true;
    }

    public boolean contains(T valueToFind) {
        if (root == null) return false;
        return root.contains(valueToFind);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
