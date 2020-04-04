package main;

import calculator.Token;
import calculator.Tokenizer;

public class Controller {

    private Tokenizer tokenizer;

    public Controller() {
        this.tokenizer = new Tokenizer();
        this.tokenizer.add("[+-]", 1);
        this.tokenizer.add("[0-9]+", 2); //integer number
        this.tokenizer.tokenize("2-98");
        for (Token token: tokenizer.getTokens()){
            System.out.println("" + token.token + " " + token.sequence);
        }
    }

}
