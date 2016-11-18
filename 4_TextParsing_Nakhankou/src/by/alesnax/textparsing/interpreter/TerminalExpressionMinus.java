package by.alesnax.textparsing.interpreter;

/**
 * Created by alesnax on 12.11.2016.
 */

public class TerminalExpressionMinus extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        double first = c.popValue();
        double second = c.popValue();

        c.pushValue(second - first);
    }
}