package labs.lab12;

import java.io.*;
import java.util.*;

/**
 * EGR 283 B01
 * PhoneList.java
 *
 * @author Rafael
 * @version 1.0 4/11/2017
 */
public class PhoneList {
    /**
     * The Tree Set data structure is appropriate because it automatically sorts the values,
     * provides methods to quickly allow lookup of values between a range,
     * and has decent insertion times.
     */
    private final TreeSet<PhoneEntry>
            firstNameSorted = new TreeSet<>(Comparator.comparing(o -> o.firstName.toLowerCase())),
            lastNameSorted = new TreeSet<>(Comparator.comparing(o -> o.lastName.toLowerCase())),
            numberSorted = new TreeSet<>(Comparator.comparing(o -> o.phoneNumber));

    /**
     * Adds a new entry
     *
     * @param firstName   first name
     * @param lastName    last name
     * @param phoneNumber phone number
     */
    public void add(String firstName, String lastName, String phoneNumber) {
        PhoneEntry entry = new PhoneEntry(firstName, lastName, phoneNumber);
        firstNameSorted.add(entry);
        lastNameSorted.add(entry);
        numberSorted.add(entry);
    }

    /**
     * @param firstName search value to match against the first name
     * @return set of Phone entries that match the value
     */
    public Set<PhoneEntry> getFromFirstName(String firstName) {
        return firstNameSorted.subSet(
                PhoneEntry.newFromFirstName(firstName),
                PhoneEntry.newFromFirstName(firstName + "\uffff"));
    }

    /**
     * @param lastName search value to match against the last name
     * @return set of Phone entries that match the value
     */
    public Set<PhoneEntry> getFromLastName(String lastName) {
        return lastNameSorted.subSet(
                PhoneEntry.newFromLastName(lastName),
                PhoneEntry.newFromLastName(lastName + "\uffff"));
    }

    /**
     * @param number search value to match against the phone number
     * @return set of Phone entries that match the value
     */
    public Set<PhoneEntry> getFromNumber(String number) {
        return numberSorted.subSet(
                PhoneEntry.newFromNumber(number),
                PhoneEntry.newFromNumber((Long.parseLong(number) + 1) + ""));
    }

    /**
     * Creates Phone List from file
     * File must be formatted: firstName lastName phoneNumber \n
     *
     * @param filePath path of the file
     * @return a phone list containing the entries of the file
     * @throws FileNotFoundException if the file does not exist
     */
    public static PhoneList listFrom(String filePath) throws FileNotFoundException {
        PhoneList phoneList = new PhoneList();

        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNext()) {
            String first = sc.next(), last = sc.next();
            String number = PhoneEntry.parseNumber(sc.nextLine());
            phoneList.add(first, last, number);
        }
        sc.close();

        return phoneList;
    }

    /**
     * Writes phone list to file
     *
     * @param filePath  the path of the file
     * @param phoneList the phone list to save
     */
    public static void writeToFile(String filePath, PhoneList phoneList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (PhoneEntry phoneEntry : phoneList.lastNameSorted) {
                String phoneNumber = PhoneEntry.formatPhoneNumber(phoneEntry.phoneNumber);
                writer.write(phoneEntry.firstName + " " + phoneEntry.lastName + " " + phoneNumber);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter File Path: ");
        final String path = sc.nextLine();
        PhoneList phoneList = PhoneList.listFrom(path);

        boolean exit = false;
        while (!exit) {
            System.out.println("Commands: add, find, print, save, exit");
            System.out.println("Enter Command: ");
            switch (sc.nextLine().toLowerCase()) {
                case "add": {
                    System.out.println("Enter Name: (format as : firstName lastName)");
                    String firstName = sc.next().trim();
                    String lastName = sc.nextLine().trim();
                    System.out.println("Enter Phone Number: (any format, 10 digits)");
                    String phoneNumber = PhoneEntry.parseNumber(sc.nextLine().trim());
                    phoneList.add(firstName, lastName, phoneNumber);
                }
                break;
                case "find": {
                    System.out.println("find by: number or name?");
                    switch (sc.nextLine().toLowerCase()) {
                        case "number":
                            System.out.println("Enter number:");
                            phoneList.getFromNumber(PhoneEntry.parseNumber(sc.nextLine()))
                                    .forEach(System.out::println);
                            break;
                        case "name":
                            System.out.println("first or last?");
                            switch (sc.nextLine().toLowerCase()) {
                                case "first":
                                    System.out.println("Enter name:");
                                    phoneList.getFromFirstName(sc.nextLine())
                                            .forEach(System.out::println);
                                    break;
                                case "last":
                                    System.out.println("Enter name:");
                                    phoneList.getFromLastName(sc.nextLine())
                                            .forEach(System.out::println);
                                    break;
                                default:
                                    System.out.println("not a valid input");
                            }
                            break;
                        default:
                            System.out.println("not a valid input");
                    }
                }
                break;
                case "print":
                    System.out.println("print in order of: number or name?");
                    TreeSet<PhoneEntry> collection;
                    switch (sc.nextLine().toLowerCase()) {
                        case "number":
                            collection = phoneList.numberSorted;
                            break;
                        case "name":
                            System.out.println("first or last?");
                            switch (sc.nextLine().toLowerCase()) {
                                case "first":
                                    collection = phoneList.firstNameSorted;
                                    break;
                                case "last":
                                    collection = phoneList.lastNameSorted;
                                    break;
                                default:
                                    System.out.println("not a valid input");
                                    continue;
                            }
                            break;
                        default:
                            System.out.println("not a valid input");
                            continue;
                    }
                    int i = 0;
                    for (PhoneEntry phoneEntry : collection) {
                        System.out.println(phoneEntry.toString());
                        if (i > 0 && i % 10 == 0) {
                            System.out.println("Page " + i / 10 + " of " + (phoneList.numberSorted.size() / 10) + ". Continue with enter. Enter exit to exit");
                            if (sc.nextLine().trim().equals("exit"))
                                break;
                        }
                        i++;
                    }
                    break;
                case "save":
                    PhoneList.writeToFile(path, phoneList);
                    System.out.println("File written");
                    break;
                case "exit":
                case "quit":
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown Command");
            }
            System.out.println();
        }
    }
}
