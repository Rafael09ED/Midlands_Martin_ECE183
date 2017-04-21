package labs.lab5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EGR 283 B01
 * CircularListTest.java
 * Purpose: Tests the Circular List
 *
 * @author Rafael
 * @version 1.0 2/2/2017
 */
class CircularListTest {
    private CircularList testList;

    private static List<String> getTestList() {
        return Arrays.asList("string1", "string2", "string3");
    }

    private static List<String> getDifferentList() {
        return Arrays.asList("number1", "number2", "number3", "number4");
    }

    @BeforeEach
    public void initialize() {
        testList = new CircularList(getTestList());
    }

    @Test
    void size() {
        assertEquals(getTestList().size(), testList.size());
        assertEquals(0, new CircularList().size());
    }

    @Test
    void isEmpty() {
        assertFalse(testList.isEmpty());
        assertTrue(new CircularList().isEmpty());
    }

    @Test
    void contains() {
        for (String s : getTestList()) {
            assertTrue(testList.contains(s));
        }
        for (String s : getDifferentList()) {
            assertFalse(testList.contains(s));
        }
    }

    @Test
    void add() {
        String toAdd = "toAddString";
        assertFalse(testList.contains(toAdd));
        testList.add(toAdd);
        assertTrue(testList.contains(toAdd));
        assertEquals(getTestList().size() + 1, testList.size());
        assertEquals(toAdd, testList.get(testList.size() - 1));

        add(0, "testVal0");
        add(1, "testVal1");
        add(getTestList().size(), "testVal1");
    }

    void add(int index, String string) {
        initialize();
        assertFalse(testList.contains(string));
        testList.add(index, string);
        assertTrue(testList.contains(string));
        assertEquals(getTestList().size() + 1, testList.size());
        assertEquals(string, testList.get(index));
    }

    @Test
    void remove() {
        for (int i = testList.size() - 1; i >= 0; i--) {
            String removed = testList.remove(i);
            assertFalse(testList.contains(removed));
            assertEquals(getTestList().get(i), removed);
            assertEquals(i, testList.size());
        }
        assertTrue(testList.isEmpty());
        remove(0);
        remove(1);
        remove(getTestList().size() - 1);

    }

    void remove(int index) {
        initialize();
        assertEquals(getTestList().get(index), testList.remove(index));
        assertNotEquals(getTestList().get(index), testList.get(index));
    }

    @Test
    void clear() {
        testList.clear();
        assertEquals(0, testList.size());
        for (String s : getTestList()) {
            assertFalse(testList.contains(s));
        }
    }

    @Test
    void get() {
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(getTestList().get(i), testList.get(i));
        }
    }

    @Test
    void set() {
        for (int i = 0; i < getDifferentList().size(); i++) {
            testList.set(i, getDifferentList().get(i));
        }
        for (int i = getDifferentList().size() - testList.size(); i < getDifferentList().size(); i++) {
            assertTrue(testList.contains(getDifferentList().get(i)));
        }
        for (String s : getTestList()) {
            assertFalse(testList.contains(s));
        }

        testList = new CircularList();
        testList.add(getTestList().get(0));
        testList.set(0, getTestList().get(0));
        assertEquals(testList.get(0), getTestList().get(0));
    }

    @Test
    void indexOf() {
        for (int i = 0; i < getTestList().size() - 1; i++) {
            assertEquals(i, testList.indexOf(getTestList().get(i)));
        }
        assertEquals(-1, testList.indexOf(getDifferentList().get(0)));
    }

    @Test
    void moveFirst() {
        LinkedList<String> movedStringList = new LinkedList<>(getTestList());
        movedStringList.addLast(movedStringList.removeFirst());

        testList.moveFirst();
        for (int i = 0; i < movedStringList.size() - 1; i++) {
            assertEquals(testList.get(i), movedStringList.get(i));
        }

        initialize(); // reset
        movedStringList.addLast(movedStringList.removeFirst());
        testList.moveFirst(2);
        for (int i = 0; i < movedStringList.size(); i++) {
            assertEquals(testList.get(i), movedStringList.get(i));
        }

        initialize(); // reset
        movedStringList = new LinkedList<>(getTestList());
        movedStringList.addFirst(movedStringList.removeLast());
        testList.moveFirst(-1);
        for (int i = 0; i < movedStringList.size(); i++) {
            assertEquals(testList.get(i), movedStringList.get(i));
        }

    }


    @AfterAll
    static void afterAll() {
        System.out.println("Tests Completed");
    }
}