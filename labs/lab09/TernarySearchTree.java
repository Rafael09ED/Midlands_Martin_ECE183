package labs.lab9;

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
    private TernaryTreeNode<T> root;

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
            System.out.println("In    Order: " + tree.toString(TernaryTreeNode.TreeView.IN_ORDER));
            System.out.println("Level Order: " + tree.toString(TernaryTreeNode.TreeView.LEVEL_ORDER));
            System.out.println();
        }
        System.out.println("Exited");
    }

    /**
     * @param valueToInsert the value to add to the tree
     * @return true if able to insert the value, false if value already exists in the tree
     */
    public boolean insert(T valueToInsert) {
        if (root == null) root = new TernaryTreeNode<>();
        return insert(valueToInsert, root);
    }

    private static <T extends Comparable<T>> boolean insert(T valueToInsert, TernaryTreeNode<T> root) {
        switch (root.compare(valueToInsert)) {
            case UPPER_BOUND:
                if (root.upperBound != null) return false;
                root.upperBound = valueToInsert;
                return true;
            case LOWER_BOUND:
                if (root.lowerBound != null) return false;
                root.lowerBound = valueToInsert;
                return true;
            case LESS:
                return insert(valueToInsert, root.getLess());
            case BETWEEN:
                return insert(valueToInsert, root.getBetween());
            case GREATER:
                return insert(valueToInsert, root.getGreater());
        }
        return false;
    }

    /**
     * Removes a value from the tree
     *
     * @param valueToRemove the value to remove
     * @return the removed value
     */
    public T remove(T valueToRemove) {
        TernaryTreeNode<T> ptr = root;
        while (ptr != null)
            switch (ptr.compare(valueToRemove)) {
                case LOWER_BOUND: {
                    if (ptr.lowerBound == null) return null;
                    T returnVal = ptr.lowerBound;
                    ptr.lowerBound = startPull(ptr, true);
                    return returnVal;
                }
                case UPPER_BOUND: {
                    if (ptr.upperBound == null) return null;
                    T returnVal = ptr.upperBound;
                    ptr.upperBound = startPull(ptr, false);
                    return returnVal;
                }
                case LESS:
                    ptr = ptr.less;
                    break;
                case BETWEEN:
                    ptr = ptr.between;
                    break;
                case GREATER:
                    ptr = ptr.greater;
                    break;
            }
        return null;
    }

    /**
     * Returns a value to replace a to be deleted value
     *
     * @param ptr        the pointer that will have a node removed
     * @param lowerBound if it is the lowerBound node that will be removed, upperBound if false
     * @return the value to replace the node with
     */
    private static <T extends Comparable<T>> T startPull(TernaryTreeNode<T> ptr, boolean lowerBound) {
        if (lowerBound) {
            if (ptr.less != null) {
                T returnVal = pull(ptr.less, true);
                if (ptr.less.lowerBound == null) ptr.less = null;
                return returnVal;
            }
            if (ptr.upperBound == null) return null;
            if (ptr.between != null) {
                T returnVal = pull(ptr, true);
                if (ptr.between.lowerBound == null) ptr.between = null;
                return returnVal;
            }
            T returnVar = ptr.upperBound;
            if (ptr.greater != null) {
                ptr.upperBound = pull(ptr.greater, false);
                if (ptr.greater.lowerBound == null) ptr.greater = null;
            }
            return returnVar;
        } else {
            if (ptr.greater != null) {
                T returnVal = pull(ptr.greater, true);
                if (ptr.greater.lowerBound == null) ptr.greater = null;
                return returnVal;
            }
            if (ptr.between != null) {
                T returnVal = pull(ptr.between, false);
                if (ptr.between.lowerBound == null) ptr.between = null;
                return returnVal;
            }
            return null;
        }
    }

    /**
     * Removes and returns either the highest or lowest value from a tree node
     *
     * @param ptr    the node that will be searched
     * @param lowest if it is the lowest value you want returned, highest if false
     * @return the removed value
     */
    private static <T extends Comparable<T>> T pull(TernaryTreeNode<T> ptr, boolean lowest) {
        if (ptr == null || ptr.lowerBound == null) return null;
        if (lowest) {
            if (ptr.less != null) {
                T returnVar = pull(ptr.less, false);
                if (ptr.less != null && ptr.less.lowerBound == null) ptr.less = null;
                return returnVar;
            }
            if (ptr.upperBound == null) {
                T returnVar = ptr.lowerBound;
                ptr.lowerBound = null;
                return returnVar;
            }
            if (ptr.between != null) {
                T returnVar = ptr.lowerBound;
                ptr.lowerBound = pull(ptr.between, true);
                if (ptr.between.lowerBound == null) ptr.between = null;
                return returnVar;
            }
            T returnVar = ptr.lowerBound;
            ptr.lowerBound = ptr.upperBound;
            if (ptr.greater != null) {
                ptr.upperBound = pull(ptr.greater, true);
                if (ptr.greater.lowerBound == null) ptr.greater = null;
            }
            return returnVar;
        } else {
            if (ptr.upperBound == null) {
                T returnVar = ptr.lowerBound;
                ptr.lowerBound = pull(ptr.less, false);
                if (ptr.less != null && ptr.less.lowerBound == null) ptr.less = null;
                return returnVar;
            }
            if (ptr.greater != null) {
                T returnVar = pull(ptr.greater, true);
                if (ptr.greater.lowerBound == null) ptr.greater = null;
                return returnVar;
            }
            if (ptr.between != null) {
                T returnVar = ptr.upperBound;
                ptr.upperBound = pull(ptr.between, false);
                if (ptr.between.lowerBound == null) ptr.between = null;
                return returnVar;
            }
            T returnVar = ptr.upperBound;
            ptr.upperBound = null;
            return returnVar;
        }
    }

    /**
     * @param valueToFind the value to find
     * @return true if the value is in the tree, false if not
     */
    public boolean contains(T valueToFind) {
        TernaryTreeNode<T> ptr = root;
        while (ptr != null)
            switch (ptr.compare(valueToFind)) {
                case LOWER_BOUND:
                    return ptr.lowerBound != null;
                case UPPER_BOUND:
                    return ptr.upperBound != null;
                case LESS:
                    ptr = ptr.less;
                    break;
                case BETWEEN:
                    ptr = ptr.between;
                    break;
                case GREATER:
                    ptr = ptr.greater;
                    break;
            }
        return false;
    }

    @Override
    public String toString() {
        return toString(TernaryTreeNode.TreeView.IN_ORDER);
    }

    private String toString(TernaryTreeNode.TreeView treeView) {
        if (root == null)
            return "";
        return root.toString(treeView);
    }
}
