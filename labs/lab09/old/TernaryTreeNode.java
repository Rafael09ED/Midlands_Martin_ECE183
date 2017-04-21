package labs.lab9.old;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EGR 283 B01
 * labs.lab9.old.labs.lab9.TernaryTreeNode.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 3/16/2017
 */
public class TernaryTreeNode<T extends Comparable<T>>  {

    private static final int TREE_VIEW_INORDER = 0, TREE_VIEW_PREORDER = 1, TREE_VIEW_POSTORDER = 2, TREE_VIEW_LEVELORDER = 3;
    private TernaryTreeNode<T> less, between, greater;
    private T lowerBound, upperBound;

    public TernaryTreeNode(T initialValue) {
        this.lowerBound = initialValue;
    }

    public boolean insert(T valueToInsert) {
        if (valueToInsert.compareTo(lowerBound) == 0 || (upperBound != null && valueToInsert.compareTo(upperBound) == 0))
            return false;
        if (valueToInsert.compareTo(lowerBound) < 0)
            if (less == null) less = new TernaryTreeNode<>(valueToInsert);
            else return less.insert(valueToInsert);
        else if (upperBound == null) upperBound = valueToInsert;
        else if (valueToInsert.compareTo(upperBound) > 0)
            if (greater == null) greater = new TernaryTreeNode<>(valueToInsert);
            else return greater.insert(valueToInsert);
        else if (between == null) between = new TernaryTreeNode<>(valueToInsert);
        else return between.insert(valueToInsert);
        return true;
    }

    public String toString(int treeView) {
        Stream<Object> treePrintableStream;
        switch (treeView) {
            case TREE_VIEW_INORDER:
                treePrintableStream = Stream.of(less, lowerBound, between, upperBound, greater);
                break;
            case TREE_VIEW_POSTORDER:
                treePrintableStream = Stream.of(less, between, greater, upperBound, lowerBound);
                break;
            case TREE_VIEW_PREORDER:
                treePrintableStream = Stream.of(lowerBound, upperBound, less, between, greater);
                break;
            case TREE_VIEW_LEVELORDER:
                List<T> list = new LinkedList<>();
                Queue<TernaryTreeNode<T>> queue = new LinkedList<>();
                queue.add(this);
                while (!queue.isEmpty()) {
                    TernaryTreeNode<T> ptr = queue.remove();
                    Stream.of(ptr.lowerBound, ptr.upperBound)
                            .filter(Objects::nonNull)
                            .forEach(list::add);
                    Stream.of(ptr.less, ptr.between, ptr.greater)
                            .filter(Objects::nonNull)
                            .forEach(queue::add);
                }
                return list.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
            default:
                throw new IllegalArgumentException();
        }
        return treePrintableStream
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return Stream.of(less, lowerBound, between, upperBound, greater)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public boolean contains(T valueToFind) {
        // return find(valueToFind) != null;
        int boundCompare = valueToFind.compareTo(lowerBound);
        if (boundCompare == 0) return true;
        else if (boundCompare < 0) return less != null && less.contains(valueToFind);
        if (upperBound == null) return false;
        boundCompare = valueToFind.compareTo(upperBound);
        if (boundCompare == 0) return true;
        else if (boundCompare > 0) return greater != null && greater.contains(valueToFind);
        return between != null && between.contains(valueToFind);
    }

    private TernaryTreeNode<T> find(T valueToFind) {
        int boundCompare = valueToFind.compareTo(lowerBound);
        if (boundCompare == 0) return this;
        else if (boundCompare < 0)
            if (less == null) return null;
            else return less.find(valueToFind);
        if (upperBound == null) return null;
        boundCompare = valueToFind.compareTo(upperBound);
        if (boundCompare == 0) return this;
        else if (boundCompare > 0)
            if (greater == null) return null;
            else return greater.find(valueToFind);
        if (between == null) return null;
        else return between.find(valueToFind);
    }

    public T delete(T valueToDelete) {
        int boundCompare = valueToDelete.compareTo(lowerBound);
        if (boundCompare == 0) {
            if (less != null) {
                T replacementVal = less.pull(false);
                if (less.lowerBound == null)
                    less = null;
                T returnVal = lowerBound;
                lowerBound = replacementVal;
                return returnVal;
            } else if (between != null) {
                T replacementVal = between.pull(true);
                if (between.lowerBound == null)
                    between = null;
                T returnVal = lowerBound;
                lowerBound = replacementVal;
                return returnVal;
            } else if (greater != null) {
                T replacementVal = greater.pull(true);
                if (greater.lowerBound == null)
                    greater = null;
                T returnVal = lowerBound;
                lowerBound = upperBound;
                upperBound = replacementVal;
                return returnVal;
            } else if (upperBound != null) {
                T returnVal = lowerBound;
                lowerBound = upperBound;
                upperBound = null;
                return returnVal;
            } else {
                valueToDelete = lowerBound;
                lowerBound = null;
                return valueToDelete;
            }
        } else if (boundCompare < 0) {
            if (less == null) return null;
            else {
                T returnVal = less.delete(valueToDelete);
                if (returnVal != null && less.lowerBound == null)
                    less = null;
                return returnVal;
            }
        }
        if (upperBound == null) return null;
        boundCompare = valueToDelete.compareTo(upperBound);
        if (boundCompare == 0) {
            if (greater != null) {
                T replacementVal = greater.pull(true);
                if (greater.lowerBound == null)
                    greater = null;
                T returnVal = upperBound;
                upperBound = replacementVal;
                return returnVal;
            } else if (between != null) {
                T replacementVal = between.pull(false);
                if (between.lowerBound == null)
                    between = null;
                T returnVal = upperBound;
                upperBound = replacementVal;
                return returnVal;
            } else {
                valueToDelete = upperBound;
                upperBound = null;
                return valueToDelete;
            }
        } else if (boundCompare > 0) {
            if (greater == null) return null;
            else {
                T returnVal = greater.delete(valueToDelete);
                if (returnVal != null && greater.lowerBound == null)
                    greater = null;
                return returnVal;
            }
        }
        if (between == null) return null;
        else {
            T returnVal = between.delete(valueToDelete);
            if (returnVal != null && between.lowerBound == null)
                between = null;
            return returnVal;
        }
    }

    private T pull(boolean lowest) {
        if (lowest) {
            if (less != null) {
                T temp = less.pull(lowest);
                if (less.lowerBound == null)
                    less = null;
                return temp;
            } else if (between != null) {
                T temp = between.pull(lowest);
                if (between.lowerBound == null)
                    between = null;
                return temp;
            } else if (greater != null) {
                T temp = greater.pull(lowest);
                if (greater.lowerBound == null)
                    greater = null;
                return temp;
            } else if (upperBound != null) {
                T temp = upperBound;
                upperBound = null;
                return temp;
            } else {
                T temp = lowerBound;
                lowerBound = null;
                return temp;
            }
        } else {
            if (greater != null) {
                T temp = greater.pull(lowest);
                if (greater.lowerBound == null)
                    greater = null;
                return temp;
            } else if (upperBound != null) {
                if (between != null) {
                    T result = between.pull(lowest);
                    if (between.lowerBound == null)
                        between = null;
                    T temp = upperBound;
                    upperBound = result;
                    return temp;
                } else {
                    T temp = upperBound;
                    upperBound = null;
                    return temp;
                }
            } else {
                if (less != null) {
                    T result = less.pull(lowest);
                    if (less.lowerBound == null)
                        less = null;
                    T temp = lowerBound;
                    lowerBound = result;
                    return temp;
                } else {
                    T temp = lowerBound;
                    lowerBound = null;
                    return temp;
                }

            }
        }

    }
}
