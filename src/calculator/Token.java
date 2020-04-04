package calculator;

/**
 * This class represents a token already read from the input
 * It contains the integer associated with the token read (int token)
 * It also contains the read string from the input (String sequence)
 */

public class Token {
    public final int token;
    public final String sequence;

    public Token(int token, String sequence){
        this.token = token;
        this.sequence = sequence;
    }
}
