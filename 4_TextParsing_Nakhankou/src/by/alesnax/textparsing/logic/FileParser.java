package by.alesnax.textparsing.logic;

import by.alesnax.textparsing.entity.TextComponent;
import by.alesnax.textparsing.parser.TextParser;
import by.alesnax.textparsing.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 02.11.2016.
 */

public class FileParser {
    private static Logger logger = LogManager.getLogger(FileParser.class);

    public TextComponent parseText(String fileName) {
        String inputText = new TextReader().readTxtFile(fileName);
        TextParser textParser = new TextParser();
        TextComponent textComponent = textParser.parse(inputText);
        logger.log(Level.INFO, "File " + fileName + " was parsed successfully");

        return textComponent;
    }
}
