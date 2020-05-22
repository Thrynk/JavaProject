package calculator;

public class AdditionNode extends SequenceNode {

    public AdditionNode() {
        super();
    }

    public AdditionNode(Node a, boolean positive) {
        super(a, positive);
    }

    public int getType() {
        return Node.ADDITION_NODE;
    }

    public double getValue() {
        double sum = 0.0;
        for (Term t : terms) {
            if (t.positive)
                sum += t.expression.getValue();
            else
                sum -= t.expression.getValue();
        }
        return sum;
    }
}