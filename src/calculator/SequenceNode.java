package calculator;

import java.util.LinkedList;

public abstract class SequenceNode implements Node {

    protected LinkedList<Term> terms;

    public SequenceNode() {
        this.terms = new LinkedList<Term>();
    }

    public SequenceNode(Node a, boolean positive) {
        this.terms = new LinkedList<Term>();
        this.terms.add(new Term(positive, a));
    }

    public void add(Node a, boolean positive) {
        this.terms.add(new Term(positive, a));
    }
}
