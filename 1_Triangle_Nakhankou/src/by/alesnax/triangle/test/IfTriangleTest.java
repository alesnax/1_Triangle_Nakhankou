package by.alesnax.triangle.test;

import by.alesnax.triangle.parser.TriangleParser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alesnax on 05.10.2016.
 */
public class IfTriangleTest {

    @Test
    public void checkIfTriangleTrue(){
        String[] points = {"1", "4", "7", "2", "2.3", "1" };
        TriangleParser parser = new TriangleParser();
        boolean actual = TriangleParser.checkIfPointsNotInLine(points);
        Assert.assertTrue("Test failed, points in the same line, ", actual);

    }


    @Test
    public void checkIfTriangleFalse(){
        String[] points = {"1", "0", "5", "0", "10", "0"};

        boolean actual = TriangleParser.checkIfPointsNotInLine(points);
        Assert.assertFalse("Test failed, points are not in the same line, ", actual);

    }


}
