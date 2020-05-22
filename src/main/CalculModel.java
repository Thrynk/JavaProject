package main;

import calculator.FunctionExpressionNode;
import calculator.VariableNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalculModel {
    private ObservableList<Calcul> calculs = FXCollections.observableArrayList();

    public ObservableList<Calcul> getCalculs(){
        return calculs;
    }

    public void addCalcul(String calcul, String result){
        Calcul calc = new Calcul(calcul, result);
        calculs.add(calc);
    }

    public ObservableList<String> getVariables(){
        return VariableNode.getVariables();
    }

    public ObservableList<String> getFunctions(){
        return FunctionExpressionNode.getFunctions();
    }
}
