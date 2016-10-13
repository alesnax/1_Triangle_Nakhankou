package by.alesnax.triangle.input;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by alesnax on 05.10.2016.
 */
public class TriangleInputReader {
    private static Logger logger = LogManager.getLogger(TriangleInputReader.class);

    public ArrayList<String> readFromTxtFile(String fileName) {
        Scanner scanner = null;
        String points = null;
        ArrayList<String> data = new ArrayList<>();

        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                points = scanner.nextLine().trim();
                data.add(points);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.FATAL, "NotFoundFileError ", e);
            throw new RuntimeException("Fatal Error", e.getCause());
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }
        return data;
    }
}
