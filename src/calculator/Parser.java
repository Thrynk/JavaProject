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

    public Node parse(List<Token> tokens) throws ParserException {
        this.tokens = new LinkedList<Token>(tokens);
        lookFirst = this.tokens.getFirst();

        Node instruction = instruction();

        if(lookFirst.token != Token.EPSILON){
            throw new ParserException("Unexpected symbol " + lookFirst.getTokenName() + " : " + lookFirst.sequence + " found");
        }

        return instruction;
    }

    private Node instruction() throws ParserException {
        // instruction -> initialization
        if(lookFirst.token == Token.INIT){
           initialization();
           return null;
        }
        // initialization -> FUNCTION_EXPRESSION VARIABLE OP_BRACKET VARIABLE CL_BRACKET EQUAL expression
        else if(lookFirst.token == Token.FUNCTION_EXPRESSION){
            function_expression();
            return null;
        }
        // instruction -> expression
        else if(lookFirst.token != Token.EPSILON){
            return expression();
        }
        // instruction -> Epsilon
        return null;
    }

    private void initialization(){
        goToNextToken();
        String variableName = lookFirst.sequence;
        goToNextToken();
        goToNextToken();
        Double value = Double.valueOf(lookFirst.sequence);
        goToNextToken();
        VariableNode.addVariable(variableName, value);
    }

    private void function_expression(){
        goToNextToken();
        String[] parts = lookFirst.sequence.split("\\(");
        String functionName = parts[0];
        String[] parts2 = parts[1].split("\\)");
        String variableName = parts2[0];
        goToNextToken();
        goToNextToken();
        List<Token> expression = List.copyOf(tokens);
        FunctionExpressionNode.addFunction(functionName, variableName, expression);
        lookFirst = new Token(0, "");
        System.out.println("finished 1");
    }

    private Node expression() throws ParserException {
        // expression -> term_with_sign sum
        Node t = termWithSign();
        return sum(t);
    }

    private Node termWithSign() throws ParserException {
        // term_with_sign -> PLUS term | MINUS term
        if(lookFirst.token == Token.PLUS || lookFirst.token == Token.MINUS){
            boolean positive = lookFirst.sequence.equals("+");
            goToNextToken();
            Node t = term();
            if(positive){
                return t;
            }
            else{
                return new AdditionNode(t, false);
            }
        }
        // term_with_sign -> term
        return term();
    }

    private Node sum(Node expression) throws ParserException {
        // sum -> PLUS term sum | MINUS term sum
        if(lookFirst.token == Token.PLUS || lookFirst.token == Token.MINUS){
            AdditionNode addition;
            if(expression.getType() == Node.ADDITION_NODE)
                addition = (AdditionNode) expression;
            else
                addition = new AdditionNode(expression, true);

            boolean positive = lookFirst.sequence.equals("+");
            goToNextToken();
            Node t = term();
            addition.add(t, positive);
            return sum(addition);
        }
        // sum -> Epsilon
        return expression;
    }

    private Node term() throws ParserException {
        // term -> factor factor_op
        Node f = factor();
        return factorOp(f);
    }

    private Node factor() throws ParserException {
        Node expression = null;
        // factor -> FUNCTION factor
        if(lookFirst.token == Token.FUNCTION){
            String function = lookFirst.sequence;
            goToNextToken();
            Node arg = factor();
            return new FunctionNode(function, arg);
        }
        // factor -> OP_BRACKET expression CL_BRACKET
        else if(lookFirst.token == Token.OP_BRACKET){
            goToNextToken();
            expression = expression();
            if(lookFirst.token != Token.CL_BRACKET){
                throw new ParserException("Unexpected symbol " + lookFirst.getTokenName() + "instead of closing bracket ) : " + lookFirst.sequence + " found");
            }
            else{
                goToNextToken();

            }
        }
        // factor -> value
        else {
            expression = value();
        }
        return expression;
    }

    private Node value() throws ParserException {
        Node expression = null;
        // value -> NUMBER
        if(lookFirst.token == Token.NUMBER){
            expression = new ConstantNode(lookFirst.sequence);
            goToNextToken();
            return expression;
        }
        // value -> VARIABLE
        else if(lookFirst.token == Token.VARIABLE){
            String variableName = lookFirst.sequence;
            expression = new VariableNode(variableName);
            goToNextToken();
        }
        // value -> FUNCTION_DEFINED
        else if(lookFirst.token == Token.FUNCTION_DEFINED){
            int indexOfFirstParenthese = lookFirst.sequence.indexOf("(");
            String[] parts = lookFirst.sequence.split("\\(", indexOfFirstParenthese + 1);
            String functionName = parts[0];

            String[] parts2 = (new StringBuilder(parts[1])).reverse().toString().split("\\)",2);
            String functionExpression = (new StringBuilder(parts2[1])).reverse().toString();

            /*System.out.println(functionName + " " + functionExpression);*/
            expression = new FunctionExpressionNode(functionName, functionExpression);
            goToNextToken();
        }
        return expression;
    }

    private Node factorOp(Node expression) throws ParserException {
        // factor_op -> MULT factor_with_sign factor_op | DIV factor_with_sign factor_op
        if(lookFirst.token == Token.MULT || lookFirst.token == Token.DIV){
            MultiplicationNode product;

            if(expression.getType() == Node.MULTIPLICATION_NODE)
                product = (MultiplicationNode) expression;
            else
                product = new MultiplicationNode(expression, true);
            boolean positive = lookFirst.sequence.equals("*");
            goToNextToken();
            Node f = factorWithSign();
            product.add(f, positive);
            return factorOp(product);
        }
        // factor_op -> Epsilon
        return expression;
    }

    private Node factorWithSign() throws ParserException {
        // factor_with_sign -> PLUS factor | MINUS factor
        if(lookFirst.token == Token.PLUS || lookFirst.token == Token.MINUS){
            boolean positive = lookFirst.sequence.equals("+");
            goToNextToken();
            Node f = factor();
            if(positive){
                return f;
            }
            else{
                return new AdditionNode(f, false);
            }
        }
        // factor_with_sign -> factor
        return factor();
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
