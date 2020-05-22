package main;

import javafx.beans.property.SimpleStringProperty;

public class Calcul {
    private SimpleStringProperty calcul;
    private SimpleStringProperty result;

    public Calcul(String calcul, String result){
        this.calcul = new SimpleStringProperty("");
        this.result = new SimpleStringProperty("");
        this.setCalcul(calcul);
        this.setResult(result);
    }

    public void setCalcul(String calcul){
       this.calcul.set(calcul);
    }

    public String getCalcul(){
        return this.calcul.get();
    }

    public void setResult(String result){
        this.result.set(result);
    }

    public String getResult(){
        return this.result.get();
    }
}
