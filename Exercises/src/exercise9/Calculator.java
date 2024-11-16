package exercise9;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {
    private TextField display;
    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    @Override
    public void start(Stage primaryStage) {
        // Create main container
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        // Create display field
        display = new TextField();
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setPrefHeight(50);
        display.setStyle("-fx-font-size: 20;");

        // Create button grid
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
        buttonGrid.setAlignment(Pos.CENTER);

        // Create number buttons
        for (int i = 0; i < 9; i++) {
            Button button = new Button(String.valueOf(i + 1));
            button.setPrefSize(50, 50);
            buttonGrid.add(button, i % 3, i / 3);
            final String number = String.valueOf(i + 1);
            button.setOnAction(e -> processNumber(number));
        }

        // Create 0 button
        Button button0 = new Button("0");
        button0.setPrefSize(50, 50);
        button0.setOnAction(e -> processNumber("0"));
        buttonGrid.add(button0, 1, 3);

        // Create operator buttons
        Button buttonPlus = new Button("+");
        Button buttonMinus = new Button("-");
        Button buttonMultiply = new Button("×");
        Button buttonDivide = new Button("÷");
        Button buttonEquals = new Button("=");

        buttonPlus.setPrefSize(50, 50);
        buttonMinus.setPrefSize(50, 50);
        buttonMultiply.setPrefSize(50, 50);
        buttonDivide.setPrefSize(50, 50);
        buttonEquals.setPrefSize(50, 50);

        buttonPlus.setOnAction(e -> processOperator("+"));
        buttonMinus.setOnAction(e -> processOperator("-"));
        buttonMultiply.setOnAction(e -> processOperator("*"));
        buttonDivide.setOnAction(e -> processOperator("/"));
        buttonEquals.setOnAction(e -> processEquals());

        // Add operator buttons to grid
        buttonGrid.add(buttonPlus, 3, 0);
        buttonGrid.add(buttonMinus, 3, 1);
        buttonGrid.add(buttonMultiply, 3, 2);
        buttonGrid.add(buttonDivide, 3, 3);
        buttonGrid.add(buttonEquals, 2, 3);

        // Clear button
        Button buttonClear = new Button("C");
        buttonClear.setPrefSize(50, 50);
        buttonClear.setOnAction(e -> {
            display.setText("");
            start = true;
            num1 = 0;
            operator = "";
        });
        buttonGrid.add(buttonClear, 0, 3);

        // Add all components to main container
        root.getChildren().addAll(display, buttonGrid);

        // Create scene and show
        Scene scene = new Scene(root);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void processNumber(String number) {
        if (start) {
            display.setText(number);
            start = false;
        } else {
            display.setText(display.getText() + number);
        }
    }

    private void processOperator(String op) {
        if (!operator.isEmpty()) {
            processEquals();
        }
        num1 = Double.parseDouble(display.getText());
        operator = op;
        start = true;
    }

    private void processEquals() {
        if (operator.isEmpty()) return;
        double num2 = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error");
                    start = true;
                    operator = "";
                    return;
                }
                break;
        }

        display.setText(String.valueOf(result));
        start = true;
        operator = "";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
