package by.alesnax.textparsing.interpreter;

import by.alesnax.textparsing.converter.Converter;

import java.math.BigDecimal;
import java.util.ArrayList;

import static by.alesnax.textparsing.constants.ExpressionOperator.OP_DECREMENT;
import static by.alesnax.textparsing.constants.ExpressionOperator.OP_INCREMENT;

// static import


public class Client {
    public static final String REGEX_BLANK = "\\p{Blank}+";
    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char MULTIPLY = '*';
    public static final char DIVIDE = '/';
    public static final String ERROR_RESULT = "Error";

    private ArrayList<AbstractMathExpression> listExpression;

    public Client() {
        listExpression = new ArrayList<>();
    }

    public String calculateExpression(String expression) {
        String result;
        Converter converter = new Converter();
        String infixExpression = converter.convertToInfixExpression(expression);
        String polishExpression = converter.convertToPolishExpression(infixExpression);
        if (ERROR_RESULT.equals(polishExpression)) {
            result = expression;
        } else {
            parse(polishExpression);
            BigDecimal bigDecimal = new BigDecimal(Double.parseDouble(calculate().toString()));
            result = bigDecimal.toString();
        }
        return result;
    }

    private void parse(String expression) {
        for (String lexeme : expression.split(REGEX_BLANK)) {
            if (lexeme.isEmpty()) {
                continue;
            }

            char temp = lexeme.charAt(0);
            switch (temp) {
                case MULTIPLY:
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case DIVIDE:
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                case PLUS:
                    if (OP_INCREMENT.getOperator().equals(lexeme)) {
                        listExpression.add(new TerminalExpressionIncrement());
                    } else {
                        listExpression.add(new TerminalExpressionPlus());
                    }
                    break;
                case MINUS:
                    if (OP_DECREMENT.getOperator().equals(lexeme)) {
                        listExpression.add(new TerminalExpressionDecrement());
                    } else {
                        listExpression.add(new TerminalExpressionMinus());
                    }
                    break;
                default:
                    listExpression.add(new NonterminalExpressionNumber(Double.parseDouble(lexeme)));
            }
        }
    }

    private Number calculate() {
        Context context = new Context();
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }
}