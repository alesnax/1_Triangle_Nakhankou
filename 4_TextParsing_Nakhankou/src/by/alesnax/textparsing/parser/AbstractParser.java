package by.alesnax.textparsing.parser;

import by.alesnax.textparsing.entity.TextComponent;

/**
 * Created by alesnax on 02.11.2016.
 */

public abstract class AbstractParser {

    protected AbstractParser successor;

    public AbstractParser() {
    }

    abstract public TextComponent parse(String content);
}
