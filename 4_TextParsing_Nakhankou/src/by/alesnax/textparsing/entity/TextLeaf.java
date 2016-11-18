package by.alesnax.textparsing.entity;

import by.alesnax.textparsing.type.TextElemType;

/**
 * Created by alesnax on 02.11.2016.
 */

public class TextLeaf implements TextComponent {
    private TextElemType elemType;
    private String content;

    public TextLeaf() {
    }

    public TextLeaf(TextElemType elemType, String content) {
        this.elemType = elemType;
        this.content = content;
    }

    public TextElemType getElemType() {
        return elemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content.concat(" ");
    }
}
