package by.alesnax.aircompany.factory;

import by.alesnax.aircompany.entity.CargoAirplane;
import by.alesnax.aircompany.parser.CargoAirplaneParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 08.10.2016.
 */
public class CargoAirPlaneFactory extends AbstractPlaneFactory {
    private static final Logger logger = LogManager.getLogger(CargoAirPlaneFactory.class);
    private static final int FIELD_NUM = 6;

    @Override
    public CargoAirplane createPlane(String[] info) {
        if(info.length ==FIELD_NUM) {
            CargoAirplaneParser parser = new CargoAirplaneParser();
            String[] correctInfo = parser.parsePlane(info);

            String id = correctInfo[0];
            String model = correctInfo[1];
            double fuelConsumption = Double.parseDouble(correctInfo[2]);
            double fuelTank = Double.parseDouble(correctInfo[3]);
            double cargoWeight = Double.parseDouble(correctInfo[4]);

            return new CargoAirplane(id, model, fuelConsumption, fuelTank, cargoWeight);
        }else{
            logger.log(Level.ERROR, "Wrong number of parameters in line with info: " + info[0] + " " + info[1] + " " + info[2]);
            return null;
        }
    }
}



