package labs.lab1;

/**
 * EGR 283 B01
 * GiftCard.java
 * Purpose: represents a gift card to which charges can be made
 *
 * @author Rafael
 * @version 1.0 1/10/2017
 */

public class GiftCard {
    private double cardValue;

    public GiftCard(double initValue) {
        this.cardValue = initValue;
    }

    /**
     * charges the gift card up to the card's value
     *
     * @param charge removes funds from the card, stopping at the card's value
     * @return the amount that was charged to the card
     */
    public double debit(double charge) {
        double valueCharged = charge;
        if (cardValue < charge) {
            valueCharged = cardValue;
        }
        cardValue -= valueCharged;
        return valueCharged;
    }

    /**
     * gets the balance of the card
     *
     * @return the balance of the card
     */
    public double getBalance() {
        return cardValue;
    }
}
