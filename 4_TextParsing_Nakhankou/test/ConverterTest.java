import by.alesnax.textparsing.converter.Converter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

// static import
import static org.junit.Assert.assertEquals;

/**
 * Created by alesnax on 18.11.2016.
 */
public class ConverterTest {
    private static Converter converter;

    @BeforeClass
    public static void initReader() {
        converter = new Converter();
    }

    @AfterClass
    public static void destroyReader() {
        converter = null;
    }

    @Test
    public void converterShouldReturnTrueInfixExpression() {
        String expression = "3+5-(++4-4/3)";
        String expected = "3 + 5 - ( ++ 4 - 4 / 3 )";
        String actual = converter.convertToInfixExpression(expression);
        assertEquals("Expression should be converted to infix notation", expected, actual);
    }

    @Test
    public void converterToPolishExpressionShouldReturnErrorResult() {
        String expression = "3 + 5 - ( ( 4 - 4 / 3 )";
        String expected = "Error";
        String actual = converter.convertToPolishExpression(expression);
        assertEquals("Expression should be converted to infix notation", expected, actual);
    }

    @Test
    public void converterToPolishExpressionShouldReturnRightResult() {
        String expression = "3 + 5 - ( 4 - 4 / 3 )";
        String expected = "3 5 + 4 4 3 / - -";
        String actual = converter.convertToPolishExpression(expression);
        assertEquals("Expression should be converted to infix notation", expected, actual);
    }


}
