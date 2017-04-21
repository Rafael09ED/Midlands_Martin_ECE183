package labs.lab2;

import javafx.event.ActionEvent;

import java.util.ArrayList;

/**
 * EGR 283 B01
 * TemperatureConverterJfx.java
 * Purpose: Creates a UI for users to convert temperatures
 *
 * @author Rafael
 * @version 1.0 1/12/2017
 */

public class TemperatureConverterJfx extends GUITemplate {
    public static void main(String[] args) {
        launch(args);
    }

    public TemperatureConverterJfx() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("To F");
        stringList.add("To C");
        set("Enter a temperature, choose \"to C\" or \"to F\" and click Go", 1, stringList, this);
    }

    /**
     * Handles OK button action
     *
     * @param arg0 arguments passed from event
     */
    @Override
    public void handle(ActionEvent arg0) {
        try {
            double val = getAsDouble(0);
            if (getSelectedRBIndex() == 0) {
                setAnswer(val + "°C is " + convertCToF(val) + "°F");
            } else {
                setAnswer(val + "°F is " + convertFToC(val) + "°C");
            }
        } catch (IllegalArgumentException e) {
            setAnswer(e.getMessage());
        }
    }

    /**
     * @param temp the temperature in C to convert to F
     * @return the temp in F
     */
    private double convertCToF(double temp) {
        return ((temp * 9 / 5) + 32);
    }

    /**
     * @param temp the temperature in F to convert to C
     * @return the temp in C
     */
    private double convertFToC(double temp) {
        return ((temp - 32) * 5 / 9);
    }
}
