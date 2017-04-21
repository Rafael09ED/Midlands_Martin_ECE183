package days.day3; /**
 * EGR 283 B01
 * days.day3.Test.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 1/17/2017
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application {
    private TextField val1, val2;
    private Button multiply;
    private Label output;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox main = new VBox();
        HBox hbox;
        Button button;

        hbox = new HBox();
        hbox.getChildren().add(new Label("First: "));
        val1 = new TextField();
        hbox.getChildren().add(val1);
        main.getChildren().add(hbox);

        hbox = new HBox();
        hbox.getChildren().add(new Label("Second: "));
        val2 = new TextField();
        hbox.getChildren().add(val2);
        main.getChildren().add(hbox);

        multiply = new Button(" Multiply ");
        multiply.setOnAction(event -> doMath());
        main.getChildren().add(multiply);

        output = new Label("Answer");
        main.getChildren().add(output);

        button = new Button(" Quit ");
        button.setOnAction(event -> System.exit(0));
        main.getChildren().add(button);

        primaryStage.setScene(new Scene(main));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void doMath() {
        try {
            output.setText(Double.valueOf(val1.getText())*Double.valueOf(val2.getText())+"");
        } catch (NumberFormatException e){
            output.setText("Not valid input or inputs");
        }
    }
}
