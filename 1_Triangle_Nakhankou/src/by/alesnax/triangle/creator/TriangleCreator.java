package by.alesnax.triangle.creator;

import by.alesnax.triangle.entity.Triangle;
import by.alesnax.triangle.parser.TriangleParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


/**
 * Created by alesnax on 05.10.2016.
 */
public class TriangleCreator {

    private static Logger logger = LogManager.getLogger(TriangleCreator.class);


    public ArrayList<Triangle> createTriangleList(String fileName){
        TriangleParser parser = new TriangleParser();
        ArrayList<Triangle> triangleList = null;

        if(fileName.endsWith(".txt")){
            triangleList = parser.parseTxtData(fileName);
        }else{
            logger.log(Level.ERROR, "No parsers for such type!");
        }
        return triangleList;
    }


}
