package by.alesnax.aircompany.input;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alesnax on 09.10.2016.
 */
public class AircraftReader {

    private static Logger logger = LogManager.getLogger(AircraftReader.class);
    private static final String DELIMETER = "[ ,;]+";

    public ArrayList<String> readFromTxtFile(String fileName) {
        Scanner scanner = null;
        String plane = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            scanner = new Scanner(new File(fileName));

            while (scanner.hasNextLine()) {
                plane = scanner.nextLine().trim();
                String[] info = plane.split(DELIMETER);
                switch (info[0].toUpperCase()){
                    case "AIRLINER":
                    case "CARGOAIRPLANE":
                    case "SPECIALPLANE":
                        result.add(plane);
                        break;
                    default: logger.log(Level.ERROR, "Wrong data in line, cannot parse it!");
                }
            }

        } catch (FileNotFoundException e) {
            logger.log(Level.FATAL, "NotFoundFileError ", e);
            throw new RuntimeException("Fatal Error", e.getCause());
        }finally{
            if(scanner != null){
                scanner.close();
            }
        }
        return result;
    }
}
