package by.alesnax.triangle.main;

import by.alesnax.triangle.creator.TriangleCreator;
import by.alesnax.triangle.entity.Triangle;


import java.util.ArrayList;

/**
 * Created by alesnax on 05.10.2016.
 */
public class Main {
    public static final String FILENAME = "text\\triangle.txt";

    public static void main(String[] args) {

        TriangleCreator creator = new TriangleCreator();
        ArrayList<Triangle> triangleList = creator.createTriangleList(FILENAME);
        ReportResult.printTriangleInfo(triangleList);
    }
}
