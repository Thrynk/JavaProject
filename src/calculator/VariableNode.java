package calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class VariableNode implements Node {
    private String name;
    private boolean isSet = false;

    private static final Map<String, Double> variables = new HashMap<String, Double>();

    public VariableNode(String name){
        this.name = name;
        if(variables.get(name) != null){
            isSet = true;
        }
    }

    public int getType(){
        return Node.VARIABLE_NODE;
    }

    public double getValue() throws ParserException {
        if(isSet){
            return variables.get(name);
        }
        else{
            throw new ParserException("Variable " + name + " was not initialized");
        }
    }

    public static void addVariable(String variableName, Double value){
        variables.put(variableName, value);
    }

    public static ObservableList<String> getVariables(){
        ObservableList<String> vars = FXCollections.observableArrayList();
        for(Map.Entry<String, Double> entry : variables.entrySet()){
            vars.add(entry.getKey() + "=" + String.valueOf(entry.getValue()));
        }
        return vars;
    }
}
