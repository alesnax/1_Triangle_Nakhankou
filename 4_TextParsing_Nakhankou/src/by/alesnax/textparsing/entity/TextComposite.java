package by.alesnax.textparsing.entity;

import by.alesnax.textparsing.type.TextElemType;

import java.util.ArrayList;

/**
 * Created by alesnax on 02.11.2016.
 */

public class TextComposite implements TextComponent {

    private ArrayList<TextComponent> elements;
    private TextElemType elemType;

    public TextComposite() {
        this.elements = new ArrayList<>();
    }

    public TextComposite(TextElemType elemType) {
        this.elemType = elemType;
        this.elements = new ArrayList<>();
    }

    public ArrayList<TextComponent> getElements() {
        return elements;
    }

    public TextElemType getElemType() {
        return elemType;
    }

    public void addElement(TextComponent comp) {
        elements.add(comp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent element : elements) {
            if (element instanceof TextLeaf) {
                sb.append(element.toString());
            } else {
                sb.append(element.toString());
            }
        }
        return sb.toString();
    }
}
