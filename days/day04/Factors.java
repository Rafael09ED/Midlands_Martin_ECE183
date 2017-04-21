package days.day4;

import javafx.event.ActionEvent;
import labs.lab2.GUITemplate;

import java.util.ArrayList;

/**
 * EGR 283 B01
 * Factors.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 1/19/2017
 */
public class Factors extends GUITemplate {
    public static void main(String[] args) {
        launch(args);
    }

    public Factors() {
        ArrayList<String> rbs = new ArrayList<>();
        for (int i = 1; i <= 20; i++)
            rbs.add("" + i);
        set("type in number", 0, rbs, this);
    }

    @Override
    public void handle(ActionEvent arg0) {
        int numToFactor = getSelectedRBIndex() + 1;
        StringBuilder builder = new StringBuilder("Factors of " + numToFactor + " are: ");
        for (int i = 1; i < numToFactor; i++)
            if (numToFactor % i == 0)
                builder.append(i + ", ");

        builder.append("and " + numToFactor + ".");
        setAnswer(builder.toString());
    }
}
