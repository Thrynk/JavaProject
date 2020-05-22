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
    public static final int VARIABLE = 5;
    public static final int FUNCTION = 6;
    public static final int OP_BRACKET = 7;
    public static final int CL_BRACKET = 8;
    public static final int NUMBER = 9;
    public static final int EQUAL = 10;
    public static final int INIT = 11;
    public static final int FUNCTION_EXPRESSION = 12;
    public static final int FUNCTION_DEFINED = 13;

    public final int token;
    public final String sequence;

    public Token(int token, String sequence){
        this.token = token;
        this.sequence = sequence;
    }

    public String getTokenName(){
        String tokenName = "";
        switch(this.token){
            case 0:
                tokenName = "EPSILON";
                break;
            case 1:
                tokenName = "PLUS";
                break;
            case 2:
                tokenName = "MINUS";
                break;
            case 3:
                tokenName = "MULT";
                break;
            case 4:
                tokenName = "DIV";
                break;
            case 5:
                tokenName = "VARIABLE";
                break;
            case 6:
                tokenName = "FUNCTION";
                break;
            case 7:
                tokenName = "OP_BRACKET";
                break;
            case 8:
                tokenName = "CL_BRACKET";
                break;
            case 9:
                tokenName = "NUMBER";
                break;
            case 10:
                tokenName = "EQUAL";
                break;
            case 11:
                tokenName = "INIT";
                break;
            case 12:
                tokenName = "FUNCTION_EXPRESSION";
                break;
            case 13:
                tokenName = "FUNCTION_DEFINED";
                break;
            default:
                tokenName = "UNKNOWN";
                break;
        }

        return tokenName;
    }
}
