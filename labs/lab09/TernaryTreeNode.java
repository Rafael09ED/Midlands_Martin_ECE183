package labs.lab9;

import java.util.*;
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
public class TernaryTreeNode<T extends Comparable<T>> {
    TernaryTreeNode<T> less, between, greater;
    T lowerBound, upperBound;

    /**
     * Finds a value's appropriate location in the node
     *
     * @param valueToFind the value to position
     * @return a TreeNodeComparison corresponding to where the value belongs
     */
    public TreeNodeComparison compare(T valueToFind) {
        if (lowerBound == null || lowerBound.compareTo(valueToFind) == 0)
            return TreeNodeComparison.LOWER_BOUND;
        if (lowerBound.compareTo(valueToFind) > 0) return TreeNodeComparison.LESS;
        if (upperBound == null || upperBound.compareTo(valueToFind) == 0) return TreeNodeComparison.UPPER_BOUND;
        if (upperBound.compareTo(valueToFind) < 0) return TreeNodeComparison.GREATER;
        return TreeNodeComparison.BETWEEN;
    }

    public int getHeight() {
        OptionalInt optionalInt = Stream.of(less, between, greater)
                .filter(Objects::nonNull)
                .mapToInt(TernaryTreeNode::getHeight)
                .max();
        if (!optionalInt.isPresent()) return 1;
        return optionalInt.getAsInt() + 1;
    }

    public String toString(TreeView treeView) {
        Stream<Object> treePrintableStream;
        switch (treeView) {
            case IN_ORDER:
                treePrintableStream = Stream.of(less, lowerBound, between, upperBound, greater);
                break;
            case POST_ORDER:
                treePrintableStream = Stream.of(less, between, greater, upperBound, lowerBound);
                break;
            case PRE_ORDER:
                treePrintableStream = Stream.of(lowerBound, upperBound, less, between, greater);
                break;
            case LEVEL_ORDER:
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
        return toString(TreeView.IN_ORDER);
    }

    public enum TreeView {
        PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER
    }

    public enum TreeNodeComparison {
        LESS, LOWER_BOUND, BETWEEN, UPPER_BOUND, GREATER
    }

    public TernaryTreeNode<T> getLess() {
        if (less == null)
            less = new TernaryTreeNode<>();
        return less;
    }

    public TernaryTreeNode<T> getGreater() {
        if (greater == null)
            greater = new TernaryTreeNode<>();
        return greater;
    }

    public TernaryTreeNode<T> getBetween() {
        if (between == null)
            between = new TernaryTreeNode<>();
        return between;
    }
}
