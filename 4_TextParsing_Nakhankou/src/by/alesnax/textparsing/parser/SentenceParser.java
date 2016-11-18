package by.alesnax.textparsing.parser;

import by.alesnax.textparsing.entity.TextComponent;
import by.alesnax.textparsing.entity.TextComposite;
import by.alesnax.textparsing.type.TextElemType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alesnax on 02.11.2016.
 */

public class SentenceParser extends AbstractParser {

    public static final String REGEXP_LEXEME = "(\\s*)([^\\s]+)(\\s*)";

    public SentenceParser() {
        this.successor = new LexemeParser();
    }

    @Override
    public TextComponent parse(String content) {
        TextComposite composite = new TextComposite(TextElemType.SENTENCE);
        Pattern p = Pattern.compile(REGEXP_LEXEME);
        Matcher matcher = p.matcher(content);

        while (matcher.find()) {
            String lexeme = matcher.group(2);
            composite.addElement(successor.parse(lexeme));
        }

        return composite;
    }
}
