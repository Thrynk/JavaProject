package calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.*;

public class FunctionExpressionNode implements Node {
    private String name;
    private String evaluationValue;
    private boolean isSet = false;

    private static final Map<String, Pair<String, List<Token>>> functions = new HashMap<String, Pair<String, List<Token>> >();

    public FunctionExpressionNode(String name, String evaluationValue){
        this.name = name;
        this.evaluationValue = evaluationValue;
        if(functions.get(name) != null) {
            this.isSet = true;
        }
    }

    public int getType(){
        return Node.FUNCTION_EXPRESSION;
    }

    public double getValue() throws ParserException {
        if(isSet){
            // replace in each token by the tokenized input evaluationValue, parse it and return Node getValue
            String variableName = functions.get(name).getKey();
            Tokenizer tempTokenizer = new Tokenizer();
            tempTokenizer.add("sin|cos|exp|ln|sqrt", 6); // function
            tempTokenizer.add("\\+", 1); // +
            tempTokenizer.add("-", 2); // -
            tempTokenizer.add("\\*", 3); // *
            tempTokenizer.add("/", 4); // / division
            tempTokenizer.add("\\=", 10); // =
            tempTokenizer.add("[0-9]+[.,]?[0-9]*", 9); // number
            tempTokenizer.add("\\(", 7); // opening bracket
            tempTokenizer.add("\\)", 8); // closing bracket
            tempTokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 5); // variable

            tempTokenizer.tokenize(this.evaluationValue);
            List<Token> tokensToInsert = tempTokenizer.getTokens();
            List<Token> tokens = List.copyOf(functions.get(name).getValue());
            List<Token> tokensResult = new ArrayList<>();
            for (Token token : tokens){
                if(token.token == 5){
                    //add OP_BRACKET
                    tokensResult.add( new Token(7, "("));
                    for(Token insertToken : tokensToInsert){
                        tokensResult.add(insertToken);
                    }
                    //add CL_BRACKET
                    tokensResult.add(new Token(8, ")"));
                }
                else{
                    tokensResult.add(token);
                }
            }

            /*System.out.println("Start :");
            for(Token token: tokens){
                System.out.println("" + token.getTokenName() + " " + token.sequence);
            }
            System.out.println("\n");

            System.out.println("To Insert :");
            for(Token token: tokensToInsert){
                System.out.println("" + token.getTokenName() + " " + token.sequence);
            }
            System.out.println("\n");

            // check tokens
            System.out.println("Result :");
            for(Token token: tokensResult){
                System.out.println("" + token.getTokenName() + " " + token.sequence);
            }*/
            Parser internParser = new Parser();
            Node expression = internParser.parse(tokensResult);
            return expression.getValue();
        }
        else{
            throw new ParserException("Function " + name + " was not initialized");
        }
    }

    public static void addFunction(String name, String variableName, List<Token> expression){
        Pair functionExpression = new Pair<String, List<Token>>(variableName, expression);
        functions.put(name, functionExpression);
    }

    public static ObservableList<String> getFunctions(){
        ObservableList<String> funcs = FXCollections.observableArrayList();
        String functionName = "";
        String variableName = "";
        String expression = "";
        for(Map.Entry<String, Pair<String, List<Token>> > entry : functions.entrySet()){
            expression = "";
            functionName = entry.getKey();
            variableName = entry.getValue().getKey();
            for(Token tok : entry.getValue().getValue()){
                expression += tok.sequence;
            }
            funcs.add(functionName + "(" + variableName + ")=" + expression);
        }
        return funcs;
    }
}
