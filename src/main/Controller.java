package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {

    private double temp = 0;
    private double sum = 0;

    @FXML
    private TextArea output;

    @FXML
    public void onNumberClicked(ActionEvent e){
        if(e.getSource() instanceof Button) {
            Button btn = (Button)e.getSource();
            output.setText(output.getText() + btn.getText().trim());
            /* System.out.println(btn.getText()); */
        }
    }

    public void onOperatorClicked(ActionEvent e) {
        if(e.getSource() instanceof Button) {
            Button btn = (Button)e.getSource();
            if(!output.getText().isEmpty()) {
                temp = Double.parseDouble(output.getText());
                switch (btn.getText()) {
                    case "/":
                        sum /= temp;
                        break;
                    case "X":
                        sum *= temp;
                        break;
                    case "+":
                        sum += temp;
                        break;
                    case "-":
                        sum -= temp;
                        break;
                    default:
                        sum = temp;
                }

            }
        }
    }
}
