package calculator;

public class FunctionNode implements Node {
    private String function;
    private Node argument;

    public FunctionNode(String function, Node argument){
        this.function = function;
        this.argument = argument;
    }

    public int getType(){
        return Node.FUNCTION_NODE;
    }

    public double getValue(){
        switch(this.function){
            case "sin":
                return Math.sin(argument.getValue());
            case "cos":
                return Math.cos(argument.getValue());
            case "exp":
                return Math.exp(argument.getValue());
            case "ln":
                return Math.log(argument.getValue());
            case "sqrt":
                return Math.sqrt(argument.getValue());
            default:
                return 0;
        }
    }
}
