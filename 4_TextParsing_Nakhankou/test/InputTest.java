import by.alesnax.textparsing.reader.TextReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

// static import
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by alesnax on 02.11.2016.
 */
public class InputTest {
    private static TextReader reader;

    @BeforeClass
    public static void initReader() {
        reader = new TextReader();
    }

    @AfterClass
    public static void destroyReader() {
        reader = null;
    }

    @Test(expected = RuntimeException.class)
    public void readerShouldThrowExceptionIfWrongFileName() {
        String fileName = "wrongFileName";
        reader.readTxtFile(fileName);
    }

    @Test
    public void readerShouldReturnString() {
        String fileName = "text/ExampleTextForTest.txt";
        String actual = reader.readTxtFile(fileName);
        String expected = "This is a test string. This is a second test string.";
        assertEquals("String read from file doesn't equal to expected string:", expected, actual);
    }

    @Test
    public void readerShouldNotReturnListWithNoArgs() {
        String fileName = "text/ExampleTextForTest.txt";
        String actual = reader.readTxtFile(fileName);
        String expected = "asf";
        assertNotEquals("String read from file is wrong:", expected, actual);
    }
}
