package labs.lab10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * EGR 283 B01
 * labs.lab10.Sorting.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 3/28/2017
 */
public class Sorting {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        sc = new Scanner(new File(sc.nextLine()));

        Sorting sorting = new Sorting();
        while (sc.hasNext()) {
            String input = sc.next().trim();
            if (input.length() <= 0)
                continue;
            sorting.insert(input);
        }
        System.out.println(sorting.toString());
    }

    /**
     * Holds the array of strings in an array where the index represents the first letter
     */
    private List<List<String>> list = new ArrayList<>();

    public Sorting() {
        for (int i = 0; i < 26; i++)
            list.add(new LinkedList<>());
    }

    /**
     * Inserts a string to be sorted and stored
     * @param input the string to sort and store
     */
    public void insert(String input) {
        try {
            list.get(getCharIndex(input.charAt(0))).add(input);
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Generates an index value from a char for letters where a = 0
     * @param val the char to convert
     * @return an int representing the letter
     */
    private int getCharIndex(char val) {
        int charVal = (char) val;
        if (val <= 122 & val >= 97)
            return charVal - 97;
        if (val <= 90 & val >= 65)
            return charVal - 65;
        throw new IllegalArgumentException("Not a letter");
    }

    @Override
    public String toString() {
        return list.stream()
                .map(strings -> strings.stream()
                        .map(String::toString)
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining(", "));
    }
}
