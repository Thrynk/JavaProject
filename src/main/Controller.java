package main;

import calculator.Parser;
import calculator.ParserException;
import calculator.Token;
import calculator.Tokenizer;

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
        this.tokenizer.tokenize("-2+3+45");
        for (Token token: tokenizer.getTokens()){
            System.out.println("" + token.token + " " + token.sequence);
        }

        this.parser = new Parser();
        try {
            this.parser.parse(this.tokenizer.getTokens());
        }
        catch(ParserException e){
            System.out.println(e);
        }
    }

}
