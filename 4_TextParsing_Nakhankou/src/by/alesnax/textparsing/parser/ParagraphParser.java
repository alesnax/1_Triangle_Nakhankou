package by.alesnax.textparsing.parser;

import by.alesnax.textparsing.entity.TextComponent;
import by.alesnax.textparsing.entity.TextComposite;
import by.alesnax.textparsing.entity.TextLeaf;
import by.alesnax.textparsing.type.TextElemType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alesnax on 02.11.2016.
 */
public class ParagraphParser extends AbstractParser {
    public static final String REGEXP_SENTENCE = "([^\\.\\?]+|[^\\.]{1,3}+)([\\.]{3}|[\\.\\?!])";

    public ParagraphParser(){
        this.successor = new SentenceParser();
    }

    @Override
    public TextComponent parse(String content) {
        TextComposite composite = new TextComposite(TextElemType.PARAGRAPH);
        Pattern p = Pattern.compile(REGEXP_SENTENCE );
        Matcher matcher = p.matcher(content);

        while (matcher.find()) {
            String sentence = matcher.group(1).trim();
            String endMark = matcher.group(2);
            composite.addElement(successor.parse(sentence));
            composite.addElement(new TextLeaf(TextElemType.PUNCT, endMark));
        }

        return composite;
    }
}
