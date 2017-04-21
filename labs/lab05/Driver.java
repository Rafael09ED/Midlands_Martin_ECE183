package labs.lab5;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * EGR 283 B01
 * Driver.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 2/2/2017
 */
public class Driver {
    public static void main(String[] args) {
        doLoop();
    }

    public static void doLoop() {
        Scanner in = new Scanner(System.in);
        CircularList list = new CircularList();
        System.out.println("Valid commands names are the interface method names");
        while (true) {
            System.out.println("Type In a Command:");
            try {
                switch (in.nextLine()) {
                    case "size":
                        System.out.println("size() = " + list.size());
                        break;
                    case "isEmpty":
                        System.out.println("isEmpty() = " + list.isEmpty());
                        break;
                    case "contains": {
                        System.out.println("Type in a string to search for:");
                        String string = in.nextLine();
                        System.out.println("contains(" + string + ") = " + list.contains(string));
                        break;
                    }
                    case "add": {
                        System.out.println("Type in a string to add");
                        String string = in.nextLine();
                        System.out.println("Type in an index or hit enter:");
                        try {
                            int i = Integer.parseInt(in.nextLine());
                            System.out.println("add(" + i + ", " + string + ")");
                            list.add(i, string);
                        } catch (NumberFormatException nfe) {
                            System.out.println("add(" + string + ")");
                            list.add(string);
                        }
                        break;
                    }
                    case "remove": {
                        System.out.println("Type in a string or index to remove");
                        String string = in.nextLine();
                        try {
                            int i = Integer.parseInt(string);
                            System.out.println("remove(" + i + ") = " + list.remove(i));
                        } catch (NumberFormatException nfe) {
                            System.out.println("remove(" + string + ") = " + list.remove(string));
                        }
                        break;
                    }
                    case "clear":
                        System.out.println("clear() =");
                        list.clear();
                        break;
                    case "get": {
                        System.out.println("Type in an index to retrieve");
                        int i = Integer.parseInt(in.nextLine());
                        System.out.println("get(" + i + ") = " + list.get(i));
                        break;
                    }
                    case "set": {
                        System.out.println("Type in a string to set");
                        String string = in.nextLine();
                        System.out.println("Type in an index or hit enter:");
                        int i = Integer.parseInt(in.nextLine());
                        System.out.println("set(" + i + ", " + string + ")");
                        list.set(i, string);
                        break;
                    }
                    case "indexOf": {
                        System.out.println("Type in a string to find the index of");
                        String string = in.nextLine();
                        System.out.println("indexOf(" + string + ") = " + list.indexOf(string));
                        break;
                    }
                    case "moveFirst": {
                        System.out.println("Hit enter or specify an offset");
                        String string = in.nextLine();
                        try {
                            int i = Integer.parseInt(string);
                            System.out.println("moveFirst(" + i + ")");
                            list.moveFirst(i);
                        } catch (NumberFormatException nfe) {
                            System.out.println("moveFirst()");
                            list.moveFirst();
                        }
                        break;
                    }
                    case "quit":
                    case "Quit":
                        return;
                    default:
                        System.out.println("Not a Recognized command");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("invalid input\n");
                in.nextLine();
            } catch (Exception e) {
                System.err.println("Exception caught:");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("List: " + list);
        }
    }
}
