package calculator;

/**
 * This class represents a token already read from the input
 * It contains the integer associated with the token read (int token)
 * It also contains the read string from the input (String sequence)
 */

public class Token {

    public static final int EPSILON = 0;
    public static final int PLUS = 1;
    public static final int MINUS = 2;
    public static final int MULT = 3;
    public static final int DIV = 4;
    public static final int RAISED = 5;
    public static final int FUNCTION = 6;
    public static final int OP_BRACKET = 7;
    public static final int CL_BRACKET = 8;
    public static final int NUMBER = 9;

    public final int token;
    public final String sequence;

    public Token(int token, String sequence){
        this.token = token;
        this.sequence = sequence;
    }
}
