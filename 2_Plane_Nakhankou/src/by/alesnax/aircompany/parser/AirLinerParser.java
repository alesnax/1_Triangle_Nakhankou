package by.alesnax.aircompany.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 09.10.2016.
 */

public class AirLinerParser extends PlaneParser{
    private static Logger logger = LogManager.getLogger(AirLinerParser.class);


    private static final String DEFAULT_SEATS_NUM = "20";
    private static final int MIN_SEATS_NUM = 1;
    private static final int MAX_SEATS_NUM = 500;
    private static final String DEFAULT_LUGGAGE_WEIGHT = "2000";
    private static final double MIN_LUGGAGE_WEIGHT = 500;



    @Override
    public String[] parsePlane(String[] info) {
            String id = checkId(info);
            String model = checkModel(info);
            String fuelConsumption = checkFuelConsumtion(info);
            String fuelTank = checkTank(info);
            String seats = checkSeats(info);
            String maxLuggageWeight = checkLuggageWeight(info);

            return new String[]{id, model, fuelConsumption, fuelTank, seats, maxLuggageWeight};
    }



    private String checkSeats(String[] info){
        String pass = info[5];
        try {
            int passNum = Integer.parseInt(pass);
            return (passNum > MIN_SEATS_NUM && passNum < MAX_SEATS_NUM ) ? pass : DEFAULT_SEATS_NUM;
        }catch (NumberFormatException e){
            logger.log(Level.WARN, "Wrong number of passengers, please check! Default number " + DEFAULT_SEATS_NUM + "  was set in aircompany: " + info[0] + " " + info[1] + " " + info[2]);
        }
        return DEFAULT_SEATS_NUM;
    }

    private String checkLuggageWeight(String[] info){
        String luggageWeight = info[6];
        try {
            double weight = Double.parseDouble(luggageWeight);
            return (weight > MIN_LUGGAGE_WEIGHT) ? luggageWeight : DEFAULT_LUGGAGE_WEIGHT;
        }catch (NumberFormatException e){
            logger.log(Level.WARN, "Wrong luggage weight, please check! Default weight " + DEFAULT_LUGGAGE_WEIGHT + "  was set in aircompany: " + info[0] + " " + info[1] + " " + info[2]);
        }
        return DEFAULT_LUGGAGE_WEIGHT;
    }

}
