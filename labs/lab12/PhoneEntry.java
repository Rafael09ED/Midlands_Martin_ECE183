package labs.lab12;

/**
 * EGR 283 B01
 * PhoneEntry.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/13/2017
 */

public class PhoneEntry {
    public final String firstName, lastName, phoneNumber;

    public PhoneEntry(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " : " + formatPhoneNumber(phoneNumber);
    }

    public static String parseNumber(String stringIn) {
        return stringIn.trim().replaceAll("[^\\d]", "");
    }

    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() >= 7)
            return phoneNumber.substring(0, 3) + "-"
                    + phoneNumber.substring(3, 6) + "-"
                    + phoneNumber.substring(6);
        return phoneNumber;
    }

    public static PhoneEntry newFromFirstName(String firstName) {
        return new PhoneEntry(firstName, "", "");
    }

    public static PhoneEntry newFromLastName(String lastName) {
        return new PhoneEntry("", lastName, "");
    }

    public static PhoneEntry newFromNumber(String number) {
        return new PhoneEntry("", "", number);
    }
}
