package by.alesnax.textparsing.parser;

import by.alesnax.textparsing.entity.TextComponent;
import by.alesnax.textparsing.entity.TextComposite;
import by.alesnax.textparsing.entity.TextLeaf;
import by.alesnax.textparsing.interpreter.Client;
import by.alesnax.textparsing.type.TextElemType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alesnax on 12.11.2016.
 */
public class LexemeParser extends AbstractParser {

    public static final String REGEX_WORD = "([\\p{Punct}&&[^\\(\\)\\+\\*\\-/]]*)([\\p{Alpha}]+)([\\p{Punct}&&[^\\(\\)\\+\\*\\-/]]*)";
    public static final String DASH = "-";
    public static final String EMPTY_SYMBOL = "";

    public LexemeParser() {
    }

    @Override
    public TextComponent parse(String content) {
        TextComposite composite = new TextComposite(TextElemType.LEXEME);
        Pattern wp = Pattern.compile(REGEX_WORD);
        Matcher wordMatcher = wp.matcher(content.trim());

        if (wordMatcher.find()) {
            String mark = wordMatcher.group(1);
            String word = wordMatcher.group(2);
            String endMark = wordMatcher.group(3);
            if (!mark.equals(EMPTY_SYMBOL)) {
                composite.addElement(new TextLeaf(TextElemType.PUNCT, mark));
            }
            composite.addElement(new TextLeaf(TextElemType.WORD, word));
            if (!endMark.isEmpty()) {
                composite.addElement(new TextLeaf(TextElemType.PUNCT, endMark));
            }
        } else if (DASH.equals(content)) {
            composite.addElement(new TextLeaf(TextElemType.PUNCT, content));
        } else {
            composite.addElement(new TextLeaf(TextElemType.MATH_EXP, new Client().calculateExpression(content)));
        }

        return composite;
    }
}
