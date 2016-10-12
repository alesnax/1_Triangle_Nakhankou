package by.alesnax.aircompany.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 09.10.2016.
 */
public class CargoAirplaneParser extends PlaneParser {
    private static Logger logger = LogManager.getLogger(CargoAirplaneParser.class);

    private static final String DEFAULT_CARGO_WEIGHT = "5000";
    private static final double MIN_CARGO_WEIGHT = 500;

    @Override
    public String[] parsePlane(String[] info) {
        String id = checkId(info);
        String model = checkModel(info);
        String fuelConsumption = checkFuelConsumtion(info);
        String fuelTank = checkTank(info);
        String cargoWeight = checkCargoWeight(info);

        return new String[]{id, model, fuelConsumption, fuelTank, cargoWeight};
    }


    private String checkCargoWeight(String[] info){
        String luggageWeight = info[5];
        try {
            double weight = Double.parseDouble(luggageWeight);
            return (weight > MIN_CARGO_WEIGHT) ? luggageWeight : DEFAULT_CARGO_WEIGHT;
        }catch (NumberFormatException e){
            logger.log(Level.WARN, "Wrong cargo weight, please check! Default weight " + DEFAULT_CARGO_WEIGHT + "  was set in aircompany: " + info[0] + " " + info[1] + " " + info[2]);
            return DEFAULT_CARGO_WEIGHT;
        }

    }
}
