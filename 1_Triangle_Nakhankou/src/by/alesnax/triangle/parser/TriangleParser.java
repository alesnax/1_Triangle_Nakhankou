package by.alesnax.triangle.parser;

import by.alesnax.triangle.entity.Point;
import by.alesnax.triangle.entity.Triangle;
import by.alesnax.triangle.input.TriangleInputReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by alesnax on 06.10.2016.
 */
public class TriangleParser {
    private static final String DELIMETER = "[ ]+";
    private static Logger logger = LogManager.getLogger(TriangleParser.class);


    public ArrayList<Triangle> parseTxtData(String fileName){
        int countCreatedTriangles = 0;
        int countWrongTriangles = 0;

        TriangleInputReader reader = new TriangleInputReader();
        ArrayList<String> data = reader.readFromTxtFile(fileName);
        ArrayList<Triangle> result = new ArrayList<>();
        String[] points = null;

        for (int i = 0; i < data.size(); i++) {

            points = data.get(i).split(DELIMETER);

            if(points.length != 6){
                logger.log(Level.ERROR, "Wrong number of parameters in line!");
                countWrongTriangles++;
                continue;
            }
            if (checkIfNumbers(points) && checkIfPointsNotInLine(points)) {
                result.add(createTriangle(points));
                countCreatedTriangles++;
            }else{
                countWrongTriangles++;
            }
        }
        logger.log(Level.INFO, countCreatedTriangles + " triangles created, " + countWrongTriangles + " has wrong input data!");
        return result;
    }


    private static boolean checkIfNumbers(String[] points) {
        boolean checked = false;
        for (int i = 0; i < points.length; i++) {
            try {
                double num = Double.parseDouble(points[i]);
            } catch (NumberFormatException e) {
                logger.log(Level.ERROR, "Wrong data in file, " + e);
                return checked;
            }
        }
        checked = true;
        return checked;
    }



    // method checkIfPointsNotInLine is public to be called from test class

    public static boolean checkIfPointsNotInLine(String[] points) {
        boolean checked = false;
        Triangle triangle = createTriangle(points);
        double sizeAB = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getB().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getB().getY()), 2));
        double sizeBC = Math.sqrt(Math.pow((triangle.getB().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getB().getY() - triangle.getC().getY()), 2));
        double sizeAC = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getC().getY()), 2));
        double perimeter = sizeAB + sizeAC + sizeBC;

        double maxSize = Math.max(sizeAB, sizeAC);
        maxSize = Math.max(maxSize, sizeBC);
        if(Math.abs(perimeter - maxSize * 2) < 0.001){
            logger.log(Level.WARN, "Points lie on the same line in " + triangle);
            return checked;
        }

        checked = true;
        return checked;
    }

    private static Triangle createTriangle(String[] points){
        Point a = new Point(Double.parseDouble(points[0]), Double.parseDouble(points[1]));
        Point b = new Point(Double.parseDouble(points[2]), Double.parseDouble(points[3]));
        Point c = new Point(Double.parseDouble(points[4]), Double.parseDouble(points[5]));

        return new Triangle(a, b, c);
    }


}
