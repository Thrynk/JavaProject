package calculator;

public class MultiplicationNode extends SequenceNode {

    public MultiplicationNode() {
        super();
    }

    public MultiplicationNode(Node a, boolean positive) {
        super(a, positive);
    }

    public int getType() {
        return Node.MULTIPLICATION_NODE;
    }

    public double getValue() {
        double prod = 1.0;
        for (Term t : terms) {
            if (t.positive)
                prod *= t.expression.getValue();
            else
                prod /= t.expression.getValue();
        }
        return prod;
    }
}