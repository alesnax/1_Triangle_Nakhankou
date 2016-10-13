package by.alesnax.triangle.main;

import by.alesnax.triangle.action.TriangleAction;
import by.alesnax.triangle.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by alesnax on 11.10.2016.
 */
public class ReportResult {
    private static Logger logger = LogManager.getLogger(ReportResult.class);


    public static void printTriangleInfo(ArrayList<Triangle> triangleList){
        TriangleAction action = new TriangleAction();
        Triangle triangle = null;

        for (int i = 0; i < triangleList.size(); i++) {
            triangle = triangleList.get(i);
            double area = action.computeTriangleArea(triangle);
            double perimeter = action.computeTrianglePerimeter(triangle);
            logger.log(Level.INFO, triangle + " has area: " + area + ", perimeter: " + perimeter);
        }
    }

}
