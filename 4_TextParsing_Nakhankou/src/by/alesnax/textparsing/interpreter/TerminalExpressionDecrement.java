package by.alesnax.textparsing.interpreter;

/**
 * Created by alesnax on 14.11.2016.
 */

public class TerminalExpressionDecrement extends AbstractMathExpression {

    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue() - 1);
    }
}
