package by.alesnax.textparsing.converter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;


/**
 * Created by alesnax on 13.11.2016.
 */
public class Converter {
    private static Logger logger = LogManager.getLogger(Converter.class);

    public static final String NUMBER_REGEX = "(^-?\\d+\\.?\\d*$)";
    public static final String STACK_STUB = "";
    public static final String REGEX_DOT = " \\. ";
    public static final String DOT = ".";
    public static final String REGEX_FIRST_UNARY_MINUS = "  - ";
    public static final String FIRST_UNARY_MINUS_REPLACE = "0 - ";
    public static final String REGEX_SCOPE_UNARY_MINUS = "\\( - ";
    public static final String SCOPE_UNARY_MINUS = "( 0 - ";
    public static final String REGEX_INCREMENT = "\\+ \\+";
    public static final String REGEX_DECREMENT = "- -";
    public static final String ERROR_RESULT = "Error";

    public static final String OP_PLUS = "+";
    public static final String OP_MINUS = "-";
    public static final String OP_DIVIDE = "/";
    public static final String OP_MULTIPLY = "*";
    public static final String OP_LEFT_SCOPE = "(";
    public static final String OP_RIGHT_SCOPE = ")";
    public static final String OP_DECREMENT = "--";
    public static final String OP_INCREMENT = "++";
    public static final String OP_END_EXP = "$";

    public String convertToInfixExpression(String expression) {
        StringBuilder sb = new StringBuilder().append("  ");
        char[] charExp = expression.toCharArray();
        int num = 0;
        boolean prevNum = false;
        boolean prevMinus = false;
        String prev = "";

        for (Character c : charExp) {
            if (Character.isDigit(c)) {
                if (prevNum) {
                    num = num * 10 + Character.getNumericValue(c);
                } else {
                    num = Character.getNumericValue(c);
                    prevNum = true;
                }
            } else {
                if (prevNum) {
                    sb.append(String.valueOf(num)).append(' ');
                    prevNum = false;

                }
                sb.append(String.valueOf(c)).append(' ');
            }
        }
        if (prevNum) {
            sb.append(String.valueOf(num));
        }

        String result = sb.toString().replaceAll(REGEX_DOT, DOT).replaceAll(REGEX_INCREMENT, OP_INCREMENT).replaceAll(REGEX_DECREMENT, OP_DECREMENT)
                .replaceAll(REGEX_FIRST_UNARY_MINUS, FIRST_UNARY_MINUS_REPLACE).replaceAll(REGEX_SCOPE_UNARY_MINUS, SCOPE_UNARY_MINUS);

        return result.trim();
    }

    public String convertToPolishExpression(String expression) {
        boolean converted = false;
        boolean failed = false;
        String[] operands = (expression + " " + OP_END_EXP).split(" ");
        ArrayDeque<String> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        int i = 0;
        String first = STACK_STUB;

        while (!converted) {
            if (!stack.isEmpty()) {
                first = stack.getLast();
            } else {
                first = STACK_STUB;
            }

            if (operands[i].matches(NUMBER_REGEX)) {
                result.append(operands[i]).append(" ");
                i++;
            }

            switch (operands[i]) {
                case OP_INCREMENT:
                case OP_DECREMENT:
                    if (STACK_STUB.equals(first) || OP_LEFT_SCOPE.equals(first)) {
                        stack.addLast(operands[i]);
                        i++;
                    } else {
                        logger.log(Level.ERROR, "Incorrect expression, please check it");
                        failed = true;
                    }
                    break;
                case OP_PLUS:
                case OP_MINUS:
                    if (STACK_STUB.equals(first) || OP_LEFT_SCOPE.equals(first)) {
                        stack.addLast(operands[i]);
                        i++;
                    } else if (OP_PLUS.equals(first) || OP_MINUS.equals(first) || OP_MULTIPLY.equals(first) || OP_DIVIDE.equals(first)) {
                        result.append(stack.removeLast()).append(" ");
                    } else {
                        logger.log(Level.ERROR, "Incorrect expression, please check it");
                        failed = true;
                    }
                    break;
                case OP_MULTIPLY:
                case OP_DIVIDE:
                    if (STACK_STUB.equals(first) || OP_LEFT_SCOPE.equals(first) || OP_MINUS.equals(first) || OP_PLUS.equals(first)) {
                        stack.addLast(operands[i]);
                        i++;
                    } else if (OP_MULTIPLY.equals(first) || OP_DIVIDE.equals(first)) {
                        result.append(stack.removeLast()).append(" ");
                    } else {
                        logger.log(Level.ERROR, "Incorrect expression, please check it");
                        failed = true;
                    }
                    break;
                case OP_LEFT_SCOPE:
                    stack.addLast(operands[i]);
                    i++;
                    break;
                case OP_RIGHT_SCOPE:
                    if (STACK_STUB.equals(first)) {
                        logger.log(Level.ERROR, "Incorrect expression, please check it");
                        failed = true;
                    } else if (OP_PLUS.equals(first) || OP_MINUS.equals(first) || OP_MULTIPLY.equals(first) || OP_DIVIDE.equals(first)) {
                        result.append(stack.removeLast()).append(" ");
                    } else if (OP_LEFT_SCOPE.equals(first)) {
                        stack.removeLast();
                        i++;
                    } else if (OP_INCREMENT.equals(first) || OP_DECREMENT.equals(first)) {
                        result.append(stack.removeLast()).append(" ");
                    } else {
                        logger.log(Level.ERROR, "Incorrect expression, please check it");
                        failed = true;
                    }
                    break;
                case OP_END_EXP:
                    if (STACK_STUB.equals(first)) {
                        converted = true;
                    } else if (OP_PLUS.equals(first) || OP_MINUS.equals(first) || OP_MULTIPLY.equals(first) || OP_DIVIDE.equals(first) || OP_INCREMENT.equals(first) || OP_DECREMENT.equals(first)) {
                        result.append(stack.removeLast()).append(" ");
                    } else if (OP_LEFT_SCOPE.equals(first)) {
                        logger.log(Level.ERROR, "Incorrect expression, please check it");
                        failed = true;
                    } else {
                        logger.log(Level.ERROR, "Incorrect expression, please check it");
                        failed = true;
                    }
                    break;
                default:
                    logger.log(Level.ERROR, "Incorrect expression, please check it");
                    failed = true;
            }
            if (failed) {
                break;
            }
        }

        if (converted) {
            return result.toString().trim();
        } else {
            return ERROR_RESULT;
        }
    }

}

