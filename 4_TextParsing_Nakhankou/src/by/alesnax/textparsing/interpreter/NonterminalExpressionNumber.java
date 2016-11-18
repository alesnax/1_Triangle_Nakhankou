package by.alesnax.textparsing.interpreter;

/**
 * Created by alesnax on 12.11.2016.
 */

public class NonterminalExpressionNumber extends AbstractMathExpression {
    private double number;

    public NonterminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
