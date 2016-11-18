package by.alesnax.textparsing.interpreter;

/**
 * Created by alesnax on 12.11.2016.
 */

public class TerminalExpressionPlus extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue() + c.popValue());
    }
}