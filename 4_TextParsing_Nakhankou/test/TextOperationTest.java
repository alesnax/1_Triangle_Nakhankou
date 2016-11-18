import by.alesnax.textparsing.entity.TextComponent;
import by.alesnax.textparsing.logic.TextOperation;
import by.alesnax.textparsing.logic.TextReconstructor;
import by.alesnax.textparsing.parser.TextParser;
import org.junit.*;

// static import
import static org.junit.Assert.assertEquals;

/**
 * Created by alesnax on 18.11.2016.
 */

public class TextOperationTest {

    private TextOperation operation;
    private String testString;
    private TextComponent c;


    @Before
    public void initTextOperation() {
        operation = new TextOperation();
        testString = "\tIt is a first string.\tMarmaris says that nothing googled are true.\tHello?";
        c = new TextParser().parse(testString);
    }

    @After
    public void destroyTextOperation() {
        operation = null;
        testString = null;
        c = null;
    }

    @Test
    public void checkIfMethodDeleteTrueLetters() {
        String expected = "\tIt is a first string.\n\tMararis say tha nothig gooled are true.\n\tHello?";
        operation.deleteNextLetters(c);
        String actual = new TextReconstructor().getParsedText(c);
        assertEquals("Method deleted wrong letters", expected, actual);
    }

    @Test
    public void checkIfReturnsLexemesSortedByFirstLetter() {
        String expected = "\ta, are\n\tfirst\n\tgoogled\n\tHello\n\tIt, is\n\tMarmaris\n\tnothing\n\tsays, string\n\tthat, true";
        String actual =operation.printLexemesInAlphabeticalOrder(c);
        assertEquals("Method doesn't sort in right order", expected, actual);
    }

    @Test
    public void checkIfMethodReturnSentencesSortedByLexemeAmount() {
        String expected = "Hello?\n" +
                "It is a first string.\n" +
                "Marmaris says that nothing googled are true.";
        String actual = operation.printSentencesSortedByLexemeNumber(c);
        assertEquals("Method doesn't sort in right order", expected, actual);
    }




}
