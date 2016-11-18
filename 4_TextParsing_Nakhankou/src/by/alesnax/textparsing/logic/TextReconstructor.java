package by.alesnax.textparsing.logic;

import by.alesnax.textparsing.entity.TextComponent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 02.11.2016.
 */
public class TextReconstructor {

    private static Logger logger = LogManager.getLogger(TextReconstructor.class);

    public static final String SPACE_TAB = " \\t";
    public static final String TAB_SPACE = "\\t ";
    public static final String LINE_TAB = "\n\t";
    public static final String TAB = "\t";
    public static final String SPACE_EXPL_MARK = " !";
    public static final String EXPL_MARK = "!";
    public static final String SPACE_COLON = " :";
    public static final String COLON = ":";
    public static final String SPACE_SEMICOLON = " ;";
    public static final String SEMICOLON =";";
    public static final String SPACE_COMMA = " ,";
    public static final String COMMA = ",";
    public static final String SPACE_DOT = " \\.";
    public static final String DOT = ".";
    public static final String SPACE_QUEST_MARK = " \\?";
    public static final String QUEST_MARK = "?";

    public String getParsedText(TextComponent component) {
        String text = component.toString();
        String result = text.substring(0, text.length()-1).replaceAll(SPACE_TAB, LINE_TAB).replaceAll(TAB_SPACE, TAB).replaceAll(SPACE_EXPL_MARK, EXPL_MARK).replaceAll(SPACE_COLON, COLON)
                .replaceAll(SPACE_SEMICOLON, SEMICOLON).replaceAll(SPACE_COMMA, COMMA).replaceAll(SPACE_DOT, DOT).replaceAll(SPACE_QUEST_MARK, QUEST_MARK);

        logger.log(Level.INFO, "Parsed text:\n" + result);
        return result;
    }
}
