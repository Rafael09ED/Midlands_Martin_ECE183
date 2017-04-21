package labs.lab1;

import java.util.Scanner;

/**
 * EGR 283 B01
 * Driver.java
 * Purpose: to apply charges to a created gift card
 *
 * @author Rafael Dejesus
 * @version 1.0 1/10/2017
 */

public class Driver {
    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type in an initial card value:");
        GiftCard giftCard = new GiftCard(scanner.nextDouble());

        do {
            System.out.println("\nType in a value to charge to the card:");
            double paymentStillOwed = makePayment(giftCard, scanner.nextDouble());
            System.out.println("The card now holds: $" + giftCard.getBalance());
            if (paymentStillOwed > 0)
                System.out.println("You still owe: $" + paymentStillOwed);
            else if (paymentStillOwed == 0)
                System.out.println("You owe nothing.");
        } while (giftCard.getBalance() > 0);
    }

    /**
     * Charges gift card and calculates any additional payments required.
     *
     * @param paymentOption the gift card to charge.
     * @param charge the amount to charge.
     * @return the amount still owed from the transaction
     */
    private double makePayment(GiftCard paymentOption, double charge) {
        double paymentMade = paymentOption.debit(charge);
        System.out.println("The card was charged: $" + paymentMade);
        return charge - paymentMade;
    }
}
