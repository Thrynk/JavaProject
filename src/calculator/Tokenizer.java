package calculator;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the tokenizer, it can return tokens from a String input
 * tokensDefinitions is a LinkedList containing all the tokens defined by the creators of the Calculator useful to tokenize the input (linked is necessary because we need a hierarchical order for tokens).
 * tokens is a LinkedList containing all the tokens read from the input
 */

public class Tokenizer {
    private LinkedList<TokenDefinition> tokensDefinitions;
    private LinkedList<Token> tokens;

    public Tokenizer(){
        tokensDefinitions = new LinkedList<TokenDefinition>();
        tokens = new LinkedList<Token>();
    }

    public LinkedList<Token> getTokens(){
        return tokens;
    }

    public void add(String regex, int token){
        tokensDefinitions.add(new TokenDefinition(Pattern.compile("^("+ regex + ")"), token));
    }

    /**
     * Tokenize the input, this function is storing all the tokens read from the input in the tokens LinkedList
     * @param input
     */
    public void tokenize(String input){
        String s = input;
        tokens.clear();

        // variable initialization
        if(s.contains("=") && !s.contains("(")){
            tokens.add(new Token(11, "init"));
        }
        // function expression
        else if(s.trim().contains(")=")){
            tokens.add(new Token(12, "function_expression"));
        }

        while(!s.equals("")){

            for(TokenDefinition definition : tokensDefinitions){
                Matcher m = definition.regex.matcher(s);
                if(m.find()){

                    String tok = m.group().trim();
                    tokens.add(new Token(definition.token, tok));

                    s = m.replaceFirst("");
                    break;
                }
            }
        }
    }
}
