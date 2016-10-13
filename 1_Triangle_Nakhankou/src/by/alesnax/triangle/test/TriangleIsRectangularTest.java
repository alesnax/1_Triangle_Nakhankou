package by.alesnax.triangle.test;

import by.alesnax.triangle.action.TriangleAction;
import by.alesnax.triangle.entity.Point;
import by.alesnax.triangle.entity.Triangle;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by alesnax on 05.10.2016.
 */
public class TriangleIsRectangularTest {

    @Test
    public void checkIfRectangularTrue(){
        Point pointA = new Point(0,0);
        Point pointB = new Point(3,0);
        Point pointC = new Point(3,4);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        TriangleAction action = new TriangleAction();
        boolean actual = action.isRectangular(triangle);
        Assert.assertTrue("Test failed, ", actual);

    }

    @Test
    public void checkIfRectangularFalse(){
        Point pointA = new Point(2,3);
        Point pointB = new Point(3,0);
        Point pointC = new Point(2,4);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        TriangleAction action = new TriangleAction();
        boolean actual = action.isRectangular(triangle);
        Assert.assertFalse("Test failed, ", actual);

    }
}
