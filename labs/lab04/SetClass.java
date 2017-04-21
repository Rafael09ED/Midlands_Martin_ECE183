package labs.lab4;

import java.text.DecimalFormat;
import java.util.*;

/**
 * EGR 283 B01
 * labs.lab4.SetClass.java
 *
 * @author Rafael
 * @version 1.0 1/26/2017
 */
public class SetClass implements SetInterface {
    private final List<Double> doubleList = new ArrayList<>();

    public static void main(String[] args) { // tests the methods and constructors of the labs.lab4.SetClass class
        List<SetClass> setClasses = new ArrayList<>();
        SetClass setClass1 = new SetClass();
        SetClass setClass2 = new SetClass(20d, 20d);
        SetClass setClass3 = new SetClass(setClass2);
        SetClass setClass4 = new SetClass(Arrays.asList(20d, 21d, 22d));

        setClasses.addAll(Arrays.asList(setClass1, setClass2, setClass3, setClass4));
        setClasses.forEach(System.out::print);
        System.out.println();
        System.out.println("Expected:\n[no values]. [20.00]. [20.00]. [20.00, 21.00, 22.00]\n");

        System.out.println(setClass1.add(20d) + " " +
                setClass2.contains(20d) + " " +
                setClass3.equals(setClass1) + " " +
                setClass4.remove(21d)
        );
        System.out.println("Expected:\ntrue true true true");
        setClasses.forEach(System.out::print);
        System.out.println("\n");

        System.out.println(setClass1.add(20d) + " " +
                setClass2.contains(21d) + " " +
                setClass3.equals(setClass4) + " " +
                setClass4.remove(21d)
        );
        System.out.println("Expected:\nfalse false false false");
        setClasses.forEach(System.out::print);
        System.out.println();
    }

    /**
     * Constructs an empty set
     */
    public SetClass() {
    }

    /**
     * Constructs a duplicate setClass
     *
     * @param setClass the labs.lab4.SetClass to duplicate
     */
    public SetClass(SetClass setClass) {
        this.doubleList.addAll(setClass.doubleList);
    }

    /**
     * Constructs a set from a list of doubles that are not necessarily unique
     *
     * @param list the list of doubles
     */
    public SetClass(List<Double> list) {
        list.forEach(this::add);
    }

    /**
     * Constructs a set from passed doubles that are not necessarily unique
     *
     * @param doubles doubles
     */
    public SetClass(Double... doubles) {
        Arrays.asList(doubles).forEach(this::add);
    }

    /**
     * Adds double to set
     *
     * @param dbl is the Double to add
     * @return true if added to the set, false if it was already there
     */
    @Override
    public boolean add(Double dbl) {
        if (contains(dbl))
            return false;
        return doubleList.add(dbl);
    }

    /**
     * Removes double from the set
     *
     * @param dbl is the Double to remove
     * @return true if removed, false if not present
     */
    @Override
    public boolean remove(Double dbl) {
        return doubleList.remove(dbl);
    }

    /**
     * @param dbl is the Double to Test
     * @return true if double is in the set, false if not
     */
    @Override
    public boolean contains(Double dbl) {
        return doubleList.contains(dbl);
    }

    /**
     * @return true if the set is empty, false if there are doubles in the set
     */
    @Override
    public boolean empty() {
        return size() <= 0;
    }

    /**
     * @return the number of values in the set
     */
    @Override
    public int size() {
        return doubleList.size();
    }

    /**
     * @param obj the object to Test equivalency against
     * @return true of the sets contain the same set of doubles
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SetClass))
            return false;
        List<Double> otherSet = ((SetClass) obj).doubleList;
        if (otherSet.size() != doubleList.size())
            return false;
        for (Double otherDouble : otherSet)
            if (!contains(otherDouble))
                return false;
        return true;
    }

    /**
     * @return a string representation of the doubles in the set
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (Double doubles : doubleList)
            builder.append(new DecimalFormat("#.00").format(doubles.doubleValue())).append(", ");
        if (empty())
            builder.append("no values");
        else
            builder.setLength(builder.length() - 2);
        builder.append("]. ");
        return builder.toString();
    }
}
