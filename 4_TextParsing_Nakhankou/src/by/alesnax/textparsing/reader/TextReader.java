package by.alesnax.textparsing.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by alesnax on 02.11.2016.
 */

public class TextReader {
    private static Logger logger = LogManager.getLogger(TextReader.class);

    public String readTxtFile(String fileName) {
        List<String> lines;
        StringBuilder sb = new StringBuilder();

        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            for (String line : lines) {
                sb.append(line).append(' ');
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File not found, please check file destination or fileName! " + e);
            throw new RuntimeException("Fatal Error", e.getCause());
        }
        logger.log(Level.INFO, "File " + fileName + " was read successfully!");
        return sb.toString().trim();
    }
}