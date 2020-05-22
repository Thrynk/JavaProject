package calculator;

public class ConstantNode implements Node {
    private double value;

    public ConstantNode(double value) {
        this.value = value;
    }

    public ConstantNode(String value) {
        this.value = Double.valueOf(value);
    }

    public double getValue() {
        return value;
    }

    public int getType() {
        return Node.CONSTANT_NODE;
    }
}