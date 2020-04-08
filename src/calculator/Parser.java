package calculator;

import java.util.LinkedList;
import java.util.List;

/**
 * This a parser for our LL (left to right) Grammar (Grammar.md file), it is parsing an input list of tokens (built by the tokenizer)
 * It is throwing a ParserException if there is an syntax error in the input
 */

public class Parser {
    private LinkedList<Token> tokens;
    private Token lookFirst;

    public void parse(List<Token> tokens) throws ParserException {
        this.tokens = new LinkedList<Token>(tokens);
        lookFirst = this.tokens.getFirst();

        this.instruction();

        if(lookFirst.token != Token.EPSILON){
            throw new ParserException("Unexpected symbol " + lookFirst.token + " : " + lookFirst.sequence + " found");
        }
    }

    private void instruction() throws ParserException {
        // instruction -> expression
        if(lookFirst.token != Token.EPSILON){
            this.expression();
        }
        // instruction -> Epsilon
    }

    private void expression() throws ParserException {
        // expression -> term sum
        term();
        sum();
    }

    private void term() throws ParserException {
        // term -> NUMBER
        if(lookFirst.token == Token.NUMBER){
            goToNextToken();
        }
        // term -> PLUS NUMBER | MINUS NUMBER
        else if(lookFirst.token == Token.PLUS || lookFirst.token == Token.MINUS){
            goToNextToken();
            goToNextToken();
        }
        else{
            throw new ParserException("Unexpected symbol " + lookFirst.getTokenName() + " : " + lookFirst.sequence + " found");
        }
    }

    private void sum() throws ParserException {
        // sum -> PLUS term sum | MINUS term sum
        if(lookFirst.token == Token.PLUS || lookFirst.token == Token.MINUS){
            goToNextToken();
            term();
            sum();
        }
        // sum -> Epsilon
    }

    private void goToNextToken(){
        tokens.pop(); // Remove previous token already processed

        // if this is the end of the input we have to return the Epsilon token otherwise we store the next token in lookFirst
        if(tokens.isEmpty()){
            lookFirst = new Token(Token.EPSILON, "");
        }
        else{
            lookFirst = tokens.getFirst();
        }
    }
}
