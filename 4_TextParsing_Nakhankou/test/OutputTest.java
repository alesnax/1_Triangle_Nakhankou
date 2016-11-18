import by.alesnax.textparsing.reader.TextReader;
import by.alesnax.textparsing.writer.TextWriter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

// static import
import static org.junit.Assert.assertEquals;

/**
 * Created by alesnax on 18.11.2016.
 */

public class OutputTest {
    private static TextWriter writer;
    private static TextReader reader;

    @BeforeClass
    public static void initWriterReader() {
        writer = new TextWriter();
        reader = new TextReader();
    }

    @AfterClass
    public static void destroyWriterReader() {
        writer = null;
        reader = null;
    }

    @Test
    public void writerShouldWriteRightText() {
        String fileName = "text/ExampleTextForWriteTest.txt";
        String expected = "It's a test string";
        writer.writeTxtFile(fileName, expected);
        String actual = reader.readTxtFile(fileName);
        assertEquals("String read from file doesn't equal to expected string:", expected, actual);
    }

}
