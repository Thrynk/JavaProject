package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    private double sum = 0;
    private boolean hasOperatorBeingPressedOnce = false;
    private String operatorPressed = "";

    @FXML
    private TextArea output;

    @FXML
    private Label operationsHistory;

    @FXML
    private Label result;

    @FXML
    public void onNumberClicked(ActionEvent e){
        if(e.getSource() instanceof Button) {
            Button btn = (Button)e.getSource();
            output.setText(output.getText() + btn.getText().trim());
            /* System.out.println(btn.getText()); */
        }
    }

    public void onOperatorClicked(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            Button btn = (Button) e.getSource();



                if (!hasOperatorBeingPressedOnce) {
                    sum = Double.parseDouble(output.getText());
                    operatorPressed = btn.getText().trim();
                    operationsHistory.setText(output.getText() + operatorPressed);
                    output.clear();
                    hasOperatorBeingPressedOnce = true;
                } else {
                    if (!output.getText().isEmpty()) {
                        double temp = Double.parseDouble(output.getText());
                        switch (operatorPressed) {
                            case "/":
                                sum /= temp;
                                break;
                            case "x":
                                sum *= temp;
                                break;
                            case "+":
                                sum += temp;
                                break;
                            case "-":
                                sum -= temp;
                                break;

                        }
                        operatorPressed = btn.getText().trim().equals("=") ? "" : btn.getText().trim();
                        operationsHistory.setText(operationsHistory.getText() + " " + output.getText() + " " + operatorPressed);
                        output.clear();
                        result.setText(String.valueOf(sum));
                    }
                }
            }

    }

    public void onEqualClicked(){
        result.setText(String.valueOf(sum));
        operationsHistory.setText(operationsHistory.getText() + " " + output.getText());
        output.clear();
    }
}
