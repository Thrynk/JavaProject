package main;

import calculator.*;

public class Controller {

    private Tokenizer tokenizer;
    private Parser parser;

    public Controller() throws ParserException {
        this.tokenizer = new Tokenizer();
        this.tokenizer.add("\\+", 1);
        this.tokenizer.add("-", 2);
        this.tokenizer.add("\\*", 3);
        this.tokenizer.add("/", 4);
        this.tokenizer.add("[0-9]+", 9); //integer number
        this.tokenizer.add("\\(", 7);
        this.tokenizer.add("\\)", 8);
        this.tokenizer.tokenize("-3*-5");
        /*for (Token token: tokenizer.getTokens()){
            System.out.println("" + token.token + " " + token.sequence);
        }*/

        this.parser = new Parser();
        try {
            Node expression = this.parser.parse(this.tokenizer.getTokens());
            System.out.println(expression.getValue());
        }
        catch(ParserException e){
            System.out.println(e);
        }

        /*AdditionNode expression = new AdditionNode();
        expression.add(new ConstantNode(2), true);
        expression.add(new ConstantNode(3), false);
        System.out.println(expression.getValue());*/
    }

}
