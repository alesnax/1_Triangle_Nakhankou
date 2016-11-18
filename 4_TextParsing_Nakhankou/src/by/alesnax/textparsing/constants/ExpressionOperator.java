package by.alesnax.textparsing.constants;

/**
 * Created by alesnax on 16.11.2016.
 */
public enum ExpressionOperator {
    OP_PLUS("+"),
    OP_MINUS("-"),
    OP_DIVIDE("/"),
    OP_MULTIPLY("*"),
    OP_LEFT_SCOPE("("),
    OP_RIGHT_SCOPE(")"),
    OP_DECREMENT("--"),
    OP_INCREMENT("++"),
    OP_END_EXP("$");

    private String operator;

    ExpressionOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }
}
