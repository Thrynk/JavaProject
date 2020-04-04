package calculator;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private LinkedList<TokenDefinition> tokensDefinitions; //contains all the definitions of the tokens necessary for the parser
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

    public void tokenize(String str){
        String s = str;
        tokens.clear();

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
