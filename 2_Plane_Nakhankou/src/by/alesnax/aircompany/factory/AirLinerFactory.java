package by.alesnax.aircompany.factory;

import by.alesnax.aircompany.entity.Airliner;
import by.alesnax.aircompany.parser.AirLinerParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 08.10.2016.
 */
public class AirLinerFactory extends AbstractPlaneFactory {
    private static final Logger logger = LogManager.getLogger(AirLinerFactory.class);
    private static final int FIELD_NUM = 7;

    @Override
    public Airliner createPlane(String[] info) {

        if (info.length == FIELD_NUM) {
            AirLinerParser parser = new AirLinerParser();
            String[] correctInfo = parser.parsePlane(info);

            String id = correctInfo[0];
            String model = correctInfo[1];
            double fuelConsumption = Double.parseDouble(correctInfo[2]);
            double fuelTank = Double.parseDouble(correctInfo[3]);
            int seats = Integer.parseInt(correctInfo[4]);
            double maxLuggageWeight = Double.parseDouble(correctInfo[5]);

            return new Airliner(id, model, fuelConsumption, fuelTank, seats, maxLuggageWeight);
        } else {
            logger.log(Level.ERROR, "Wrong number of parameters in line with info: " + info[0] + " " + info[1] + " " + info[2]);
            return null;
        }
    }
}