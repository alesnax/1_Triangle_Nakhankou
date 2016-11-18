package by.alesnax.textparsing.writer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by alesnax on 14.11.2016.
 */

public class TextWriter {
    private static Logger logger = LogManager.getLogger(TextWriter.class);

    public void writeTxtFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            logger.log(Level.INFO, "Text was written to file " + fileName);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while writing to file" + fileName + e.getCause());
        }
    }
}
