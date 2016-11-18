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
public class TextParser extends AbstractParser {
    public static final String REGEX_PARAGRAPH = "(\\t|^)([.[^\\t]]+)";
    public static final String TAB = "\t";

    public TextParser(){
        this.successor = new ParagraphParser();
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite composite = new TextComposite(TextElemType.TEXT);
        Pattern p = Pattern.compile(REGEX_PARAGRAPH);
        Matcher matcher = p.matcher(text);

        while(matcher.find()){
            String tab = matcher.group(1);
            composite.addElement(new TextLeaf(TextElemType.TAB, TAB));
            String paragraph = matcher.group(2).trim();
            composite.addElement(successor.parse(paragraph));
        }
        return composite;
    }
}
