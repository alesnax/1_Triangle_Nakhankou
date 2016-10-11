package by.alesnax.triangle.action;


import by.alesnax.triangle.entity.Triangle;
import java.util.Arrays;


/**
 * Created by alesnax on 05.10.2016.
 */
public class TriangleAction {

    public double computeTriangleArea(Triangle triangle){
        double sizeAB = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getB().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getB().getY()), 2));
        double sizeBC = Math.sqrt(Math.pow((triangle.getB().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getB().getY() - triangle.getC().getY()), 2));
        double sizeAC = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getC().getY()), 2));
        double p = (sizeAB + sizeAC + sizeBC) / 2;
        double area = Math.sqrt(p * (p - sizeAB) * (p - sizeAC) * (p - sizeBC));

        return area;
    }


    public double computeTrianglePerimeter(Triangle triangle){
        double sizeAB = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getB().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getB().getY()), 2));
        double sizeBC = Math.sqrt(Math.pow((triangle.getB().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getB().getY() - triangle.getC().getY()), 2));
        double sizeAC = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getC().getY()), 2));

        return sizeAB + sizeBC + sizeAC;
    }

    public boolean isRectangular(Triangle triangle){
        boolean isRect = false;

        double sizeAB = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getB().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getB().getY()), 2));
        double sizeBC = Math.sqrt(Math.pow((triangle.getB().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getB().getY() - triangle.getC().getY()), 2));
        double sizeAC = Math.sqrt(Math.pow((triangle.getA().getX() - triangle.getC().getX()), 2) + Math.pow((triangle.getA().getY() - triangle.getC().getY()), 2));
        double[] sizes = new double[]{sizeAB, sizeAC, sizeBC};
        Arrays.sort(sizes);
        if((Math.pow(sizes[2], 2) - Math.pow(sizes[1], 2) - Math.pow(sizes[0], 2)) < 0.0001){
            isRect = true;
        }
        return isRect;
    }
}
