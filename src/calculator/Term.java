package calculator;

public class Term {
    public boolean positive;
    public Node expression;

    public Term(boolean positive, Node expression) {
        this.positive = positive;
        this.expression = expression;
    }
}