package by.alesnax.aircompany.factory;

import by.alesnax.aircompany.entity.SpecialPlane;
import by.alesnax.aircompany.parser.SpecialPlaneParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 08.10.2016.
 */
public class SpecialPlaneFactory extends AbstractPlaneFactory {
    private static final Logger logger = LogManager.getLogger(SpecialPlaneFactory.class);
    private static final int FIELD_NUM = 7;


    @Override
    public SpecialPlane createPlane(String[] info) {
        if(info.length ==FIELD_NUM) {
        SpecialPlaneParser parser = new SpecialPlaneParser();
        String[] correctInfo = parser.parsePlane(info);

        String id = correctInfo[0];
        String model = correctInfo[1];
        double fuelConsumption = Double.parseDouble(correctInfo[2]);
        double fuelTank = Double.parseDouble(correctInfo[3]);
        double usefulWeight = Double.parseDouble(correctInfo[4]);
        String type = correctInfo[5];

        return new SpecialPlane(id, model, fuelConsumption, fuelTank, usefulWeight, type);
        }else{
            logger.log(Level.ERROR, "Wrong number of parameters in line with info: " + info[0] + " " + info[1] + " " + info[2]);
            return null;
        }
    }
}
