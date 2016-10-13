package by.alesnax.aircompany.factory;

import by.alesnax.aircompany.constant.Constants;
import by.alesnax.aircompany.entity.Plane;
import by.alesnax.aircompany.entity.TypePlane;
import by.alesnax.aircompany.input.AircraftReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.util.ArrayList;

/**
 * Created by alesnax on 08.10.2016.
 */
public class Creator {

    private static Logger logger = LogManager.getLogger(Creator.class);


    public ArrayList<Plane> createAirCraft(String fileName) {
        AircraftReader reader = new AircraftReader();
        ArrayList<String> inputData = reader.readFromTxtFile(fileName);
        ArrayList<Plane> airCraft = new ArrayList<>();

        AbstractPlaneFactory airLinerFactory = new AirLinerFactory();
        AbstractPlaneFactory cargoAirPlaneFactory = new CargoAirPlaneFactory();
        AbstractPlaneFactory specialPlaneFactory = new SpecialPlaneFactory();

        for (String line : inputData) {
            String[] info = line.trim().split(Constants.PATTERN_SPLIT_INFO);
            TypePlane type = TypePlane.valueOf(info[0].toUpperCase());
            Plane plane = null;
            switch (type) {
                case AIRLINER:
                    plane = airLinerFactory.createPlane(info);
                    break;
                case CARGOAIRPLANE:
                    plane = cargoAirPlaneFactory.createPlane(info);
                    break;
                case SPECIALPLANE:
                    plane = specialPlaneFactory.createPlane(info);
                    break;
                default:
                    logger.log(Level.ERROR, "Wrong classTypeException");
            }
            if (plane != null) {
                airCraft.add(plane);
            }
        }
        return airCraft;
    }
}